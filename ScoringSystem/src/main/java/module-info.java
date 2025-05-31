module ScoringSystem {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires com.fasterxml.jackson.annotation;

    exports dk.sdu.cbse.scoringsystem;
    opens dk.sdu.cbse.scoringsystem to spring.core, spring.web, spring.context, spring.beans;
}