import sbt.Keys._
import sbt._

import org.scalastyle.sbt.ScalastylePlugin.autoImport._
import wartremover.WartRemover.autoImport._
import org.wartremover.contrib.ContribWarts.autoImport._
// TODO: re-enable after a version for Scala 2.13.0 is available
// import org.danielnixon.extrawarts.ExtraWart

object Linting extends Linting

trait Linting {

  val lib = Dependencies

  val scalastyleConfiguration = Seq(
    scalastyleFailOnError := true,
    test in Test := test.in(Test)
      .dependsOn(scalastyle.in(Test).toTask(""))
      .dependsOn(scalastyle.in(Compile).toTask(""))
      .value
  )

  val stdWarts = Warts.allBut(
    Wart.Any,
    Wart.AnyVal,
    Wart.AsInstanceOf,
    Wart.DefaultArguments,
    Wart.Equals,
    Wart.ImplicitConversion,
    Wart.ImplicitParameter,
    Wart.IsInstanceOf,
    Wart.MutableDataStructures,
    Wart.NonUnitStatements,
    Wart.Nothing,
    Wart.Null,
    Wart.Overloading,
    Wart.Recursion,
    Wart.Return,
    Wart.Throw,
    Wart.ToString,
    Wart.StringPlusAny,
    Wart.Var,
    Wart.While
  )

  val contribWarts = Seq(
    ContribWart.Apply,
    ContribWart.NoNeedForMonad,
    ContribWart.MissingOverride,
    ContribWart.OldTime,
    ContribWart.RefinedClasstag,
    ContribWart.SealedCaseClass,
    ContribWart.SomeApply,
    ContribWart.UnintendedLaziness
  )

  val wartremoverConfiguration = Seq(
    wartremoverExcluded += baseDirectory.value / "src" / "main" / "scala" / "scroll" / "internal" / "formal",
    wartremoverErrors ++= stdWarts ++ contribWarts
  )

  val staticAnalysis =
    scalastyleConfiguration ++
      wartremoverConfiguration

}
