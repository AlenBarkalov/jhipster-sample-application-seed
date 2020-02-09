package com.alen.barkalov.myapp;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.alen.barkalov.myapp");

        noClasses()
            .that()
                .resideInAnyPackage("com.alen.barkalov.myapp.service..")
            .or()
                .resideInAnyPackage("com.alen.barkalov.myapp.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.alen.barkalov.myapp.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
