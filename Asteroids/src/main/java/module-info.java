module Asteroids {
    requires Common;
    requires spring.web;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.asteroids.AsteroidSpawnSystem, dk.sdu.cbse.asteroids.AsteroidControlSystem;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.asteroids.AsteroidPlugin;
    provides dk.sdu.cbse.common.services.IPostEntityProcessingService with dk.sdu.cbse.asteroids.AsteroidPostProcessingSystem;
    provides dk.sdu.cbse.common.services.IScoreService with dk.sdu.cbse.asteroids.ScoreClient;
}