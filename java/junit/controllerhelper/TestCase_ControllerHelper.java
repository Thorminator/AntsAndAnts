package controllerhelper;

import controllerhelper.mocks.MockEntity;
import controllerhelper.mocks.MockEntityBuilder;
import main.java.exceptions.IllegalModificationException;
import main.java.external.control.ControllerHelper;
import main.java.control.GameController;
import main.java.control.impl.ControllerHelperImpl;
import main.java.control.impl.GameControllerImpl;
import main.java.external.entity.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

public class TestCase_ControllerHelper {
    ControllerHelper ctrlHelper;
    GameController gc;

    @BeforeEach
    public void beforeEach() {
        gc = createMockGameController();
        ctrlHelper = new ControllerHelperImpl(gc);
    }

    private GameController createMockGameController() {
        return new GameControllerImpl();
    }

    @Test
    void testGetEntitiesSortedByDistanceToGivenEntity() throws Exception {
        // Arrange
        Point2D.Float pointOne = new Point2D.Float(1, 1);
        Point2D.Float pointTwo = new Point2D.Float(5, 5);
        Point2D.Float pointThree = new Point2D.Float(42, 420);
        Point2D.Float pointFour = new Point2D.Float(69, 69);

        MockEntity myEntity = new MockEntityBuilder().setPosition(pointOne).createMockEntity();
        gc.addEntity(myEntity);
        gc.addEntity(new MockEntityBuilder().setPosition(pointTwo).createMockEntity());
        gc.addEntity(new MockEntityBuilder().setPosition(pointThree).createMockEntity());
        gc.addEntity(new MockEntityBuilder().setPosition(pointFour).createMockEntity());

        // Act
        List<Map.Entry<Entity, Float>> entitiesByDistanceToGivenEntity = ctrlHelper.getEntitiesSortedByDistanceToGivenEntity(myEntity);

        // Assert
        assertEquals(3, entitiesByDistanceToGivenEntity.size());
        assertEquals(entitiesByDistanceToGivenEntity.get(0).getValue(), (float) pointTwo.distance(pointOne));
        assertEquals(entitiesByDistanceToGivenEntity.get(1).getValue(), (float) pointFour.distance(pointOne));
        assertEquals(entitiesByDistanceToGivenEntity.get(2).getValue(), (float) pointThree.distance(pointOne));
    }

    /**
     * Empty GameController with no entities. We should expect an exception to be thrown if an entity is provided
     * because the system doesn't know the id.
     */
    @Test
    void testGetEntitiesSortedByDistanceToGivenEntityThrowsIfUnexpectedEntity() {
        Assertions.assertThrows(IllegalModificationException.class, () -> ctrlHelper
                .getEntitiesSortedByDistanceToGivenEntity(new MockEntityBuilder().createMockEntity()));
    }
}
