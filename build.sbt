inThisBuild(Seq(
  scalaVersion := "2.13.16",
  organization := "io.github.nafg.antd",
  useYarn      := true
))

publish / skip := true

def settings(flavor: Flavour) =
  Seq(
    name                      := s"antd-${flavor.id}",
    stFlavour                 := flavor,
    stMinimize                := Selection.AllExcept("antd"),
    stOutputPackage           := "io.github.nafg.antd.facade",
    Compile / npmDependencies := Seq(
      "@types/react"     -> "18.2.47",
      "@types/react-dom" -> "18.2.18",
      "antd"             -> "5.25.1"
    ),
    Test / npmDependencies    := Seq(
      "react"     -> "18.3.1",
      "react-dom" -> "18.3.1"
    )
  )

val slinky       = project.enablePlugins(ScalablyTypedConverterGenSourcePlugin).settings(settings(Flavour.Slinky))
val slinkyNative = project.enablePlugins(ScalablyTypedConverterGenSourcePlugin).settings(settings(Flavour.SlinkyNative))
val scalajsReact = project.enablePlugins(ScalablyTypedConverterGenSourcePlugin).settings(settings(Flavour.ScalajsReact))
