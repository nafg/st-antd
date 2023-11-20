inThisBuild(Seq(
  scalaVersion := "2.13.12",
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
      "@types/react"     -> "18.2.37",
      "@types/react-dom" -> "18.2.15",
      "antd"             -> "4.17.3"
    ),
    Test / npmDependencies    := Seq(
      "react"     -> "18.2.0",
      "react-dom" -> "18.2.0"
    )
  )

val slinky       = project.enablePlugins(ScalablyTypedConverterGenSourcePlugin).settings(settings(Flavour.Slinky))
val slinkyNative = project.enablePlugins(ScalablyTypedConverterGenSourcePlugin).settings(settings(Flavour.SlinkyNative))
val scalajsReact = project.enablePlugins(ScalablyTypedConverterGenSourcePlugin).settings(settings(Flavour.ScalajsReact))
