package controllerhelper;

import controllerhelper.mocks.EntityBuilder;
import controllerhelper.mocks.MockEntity;
import main.java.exceptions.IllegalModificationException;
import main.java.external.control.ControllerHelper;
import main.java.control.GameController;
import main.java.control.impl.ControllerHelperImpl;
import main.java.control.impl.GameControllerImpl;
import main.java.external.entity.AntEntity;
import main.java.external.entity.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

public class TestCase_ControllerHelper {
    private ControllerHelper ctrlHelper;
    private GameController gc;

    @BeforeEach
    void beforeEach() {
        gc = createMockGameController();
        ctrlHelper = new ControllerHelperImpl(gc);
    }

    private GameController createMockGameController() {
        return new GameControllerImpl();
    }

    @Test
    void testGetEntitiesSortedByDistanceToGivenEntity() throws Exception {
        // Arrange
        Point2D.Float positionOne = new Point2D.Float(1, 1);
        Point2D.Float positionTwo = new Point2D.Float(5, 5);
        Point2D.Float positionThree = new Point2D.Float(42, 420);
        Point2D.Float positionFour = new Point2D.Float(69, 69);

        Entity myEntity = new EntityBuilder(MockEntity.class).setPosition(positionOne).createEntity();
        gc.addEntity(myEntity);
        gc.addEntity(new EntityBuilder(MockEntity.class).setPosition(positionTwo).createEntity());
        gc.addEntity(new EntityBuilder(MockEntity.class).setPosition(positionThree).createEntity());
        gc.addEntity(new EntityBuilder(MockEntity.class).setPosition(positionFour).createEntity());

        // Act
        List<Map.Entry<Entity, Float>> entitiesByDistanceToGivenEntity = ctrlHelper.getEntitiesSortedByDistanceToGivenEntity(myEntity);

        // Assert
        assertEquals(3, entitiesByDistanceToGivenEntity.size());
        assertEquals(entitiesByDistanceToGivenEntity.get(0).getValue(), (float) positionTwo.distance(positionOne));
        assertEquals(entitiesByDistanceToGivenEntity.get(1).getValue(), (float) positionFour.distance(positionOne));
        assertEquals(entitiesByDistanceToGivenEntity.get(2).getValue(), (float) positionThree.distance(positionOne));
    }

    /**
     * Empty GameController with no entities. We should expect an exception to be thrown if an entity is provided
     * because the system doesn't know the id.
     */
    @Test
    void testGetEntitiesSortedByDistanceToGivenEntityThrowsIfUnexpectedEntity() {
        Assertions.assertThrows(IllegalModificationException.class, () -> ctrlHelper
                .getEntitiesSortedByDistanceToGivenEntity(new EntityBuilder(MockEntity.class).createEntity()));
    }

    @Test
    void testGetClosestEntityOfType() throws Exception {
        // Arrange
        Point2D.Float positionOne = new Point2D.Float(5, 5);
        Point2D.Float positionTwo = new Point2D.Float(10, 10);
        Point2D.Float positionThree = new Point2D.Float(15, 15);

        Entity myEntity = new EntityBuilder(AntEntity.class).setPosition(positionOne).createEntity();
        Entity closestExpectedAntEntity = new EntityBuilder(AntEntity.class).setPosition(positionTwo).createEntity();
        gc.addEntity(myEntity);
        gc.addEntity(new EntityBuilder(MockEntity.class).setPosition(positionOne).createEntity());
        gc.addEntity(new EntityBuilder(MockEntity.class).setPosition(positionThree).createEntity());
        gc.addEntity(closestExpectedAntEntity);

        // Act
        Map.Entry<Entity, Float> closestActualEntity = ctrlHelper.getClosestEntityOfType(myEntity, AntEntity.class);

        // Assert
        assertNotNull(closestActualEntity);
        assertEquals(closestExpectedAntEntity.getId(), closestActualEntity.getKey().getId());
    }

    @Test
    void testGetClosestEntityOfTypeNoMatchingType() throws Exception {
        // Arrange
        Point2D.Float positionOne = new Point2D.Float(5, 5);

        Entity myEntity = new EntityBuilder(AntEntity.class).setPosition(positionOne).createEntity();
        gc.addEntity(myEntity);
        gc.addEntity(new EntityBuilder(MockEntity.class).setPosition(positionOne).createEntity());

        // Act
        Map.Entry<Entity, Float> closestActualEntity = ctrlHelper.getClosestEntityOfType(myEntity, AntEntity.class);

        // Assert
        assertNull(closestActualEntity);
    }

    @Test
    void testGetClosestEntityOfTypeUsingBaseType() throws Exception {
        // Arrange
        Point2D.Float positionOne = new Point2D.Float(5, 5);
        Point2D.Float positionTwo = new Point2D.Float(10, 10);
        Point2D.Float positionThree = new Point2D.Float(15, 15);

        Entity myEntity = new EntityBuilder(AntEntity.class).setPosition(positionOne).createEntity();
        Entity closestExpectedEntity = new EntityBuilder(MockEntity.class).setPosition(positionOne).createEntity();
        gc.addEntity(myEntity);
        gc.addEntity(new EntityBuilder(MockEntity.class).setPosition(positionThree).createEntity());
        gc.addEntity(closestExpectedEntity);
        gc.addEntity(new EntityBuilder(AntEntity.class).setPosition(positionTwo).createEntity());

        // Act
        Map.Entry<Entity, Float> closestActualEntity = ctrlHelper.getClosestEntityOfType(myEntity, Entity.class);

        // Assert
        assertNotNull(closestActualEntity);
        assertEquals(closestExpectedEntity.getId(), closestActualEntity.getKey().getId());
    }
}
