package com.samsol.cuber.services.logic;

import com.samsol.cuber.CuberWebService;
import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.services.logic.helpers.GraphForMapService;
import com.samsol.cuber.services.logic.helpers.NodeForMapService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CuberWebService.class)
public class MapServiceTest {

    @Autowired
    private MapService mapService;

    @Before
    public void setup() {

    }

    @Test
    public void shouldFindMinLength() {
        long fromId = 3L;
        long toId = 6L;
        double length = mapService.calculateLength(fromId, toId);
        double lengthFromTheEnd = mapService.calculateLength(toId, fromId);
        assertThat(length).isEqualTo(15.5);
        assertThat(lengthFromTheEnd).isEqualTo(15.5);
    }

    @Test
    public void shouldFindClosestCourier() {
        DeliveryOrderDto deliveryOrder = new DeliveryOrderDto();
        deliveryOrder.setFromNodeId(3L);
        CourierDto closestCourier = mapService.findClosestCourier(deliveryOrder);

        assertThat(closestCourier.getId()).isEqualTo(134L);
    }

    @Test
    public void shouldCalculatedPrice() throws Exception {
        assertThat(mapService.calculatePrice(22.4)).isEqualTo(76.16);
    }

    @Test
    public void shouldGenerateGraphFromNodeWithId() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = MapServiceImpl.class.getDeclaredMethod("generateGraphFromNodeWithId", Long.class);
        method.setAccessible(true);
        GraphForMapService graphForMapService = (GraphForMapService) method.invoke(mapService, 5L);
        assertThat(graphForMapService).isNotNull();
    }

    @Test(expected = InvocationTargetException.class)
    public void shouldNotGenerateGraph_causeOfOutOfBoundNode() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = MapServiceImpl.class.getDeclaredMethod("generateGraphFromNodeWithId", Long.class);
        method.setAccessible(true);
        method.invoke(mapService, 999999999999999999L);
    }
}
