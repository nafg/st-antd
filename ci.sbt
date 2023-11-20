import scala.collection.JavaConverters.*

import _root_.io.github.nafg.mergify.dsl.*

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.RepositoryBuilder

mergifyExtraConditions := Seq(
  (Attr.Author :== "scala-steward") ||
    (Attr.Author :== "nafg-scala-steward[bot]")
)

val repo      = new RepositoryBuilder().findGitDir().build()
val jgit      = new Git(repo)
val remoteUrl = jgit.remoteList().call().asScala.filter(_.getName == "origin").flatMap(_.getURIs.asScala).headOption

inThisBuild(List(
  homepage                := remoteUrl.map(u => url(s"https://${u.getHost}/${u.getPath.stripSuffix(".git")}")),
  licenses                := List("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0")),
  developers              := List(
    Developer("nafg", "Naftoli Gugenheim", "98384+nafg@users.noreply.github.com", url("https://github.com/nafg"))
  ),
  dynverGitDescribeOutput ~= (_.map(o => o.copy(dirtySuffix = sbtdynver.GitDirtySuffix("")))),
  dynverSonatypeSnapshots := true,
  githubWorkflowTargetTags ++= Seq("v*"),
  githubWorkflowPublishTargetBranches := Seq(RefPredicate.StartsWith(Ref.Tag("v"))),
  githubWorkflowPublish               := Seq(
    WorkflowStep.Sbt(
      List("ci-release"),
      env = Map(
        "PGP_PASSPHRASE"    -> "${{ secrets.PGP_PASSPHRASE }}",
        "PGP_SECRET"        -> "${{ secrets.PGP_SECRET }}",
        "SONATYPE_PASSWORD" -> "${{ secrets.SONATYPE_PASSWORD }}",
        "SONATYPE_USERNAME" -> "${{ secrets.SONATYPE_USERNAME }}"
      )
    )
  )
))

sonatypeProfileName := "io.github.nafg"
