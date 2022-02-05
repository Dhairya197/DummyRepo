package com.epam.bdd.StepDef;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/com/epam/bdd/feature/first_Feature.feature", glue = { "com.epam.bdd.StepDef" })
public class runner {

}
