module Collision {
    requires Common;
    provides dk.sdu.cbse.common.services.IPostEntityProcessingService with dk.sdu.cbse.collisionsystem.CollisionDetector;
}