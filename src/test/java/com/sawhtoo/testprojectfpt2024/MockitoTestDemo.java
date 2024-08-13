package com.sawhtoo.testprojectfpt2024;

import com.sawhtoo.testprojectfpt2024.model.EngineModel;
import com.sawhtoo.testprojectfpt2024.model.Game;
import com.sawhtoo.testprojectfpt2024.model.PersonModel;
import com.sawhtoo.testprojectfpt2024.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MockitoTestDemo {

    private static final Logger log = LoggerFactory.getLogger(MockitoTestDemo.class);

    @Mock
    EngineModel engineModel;

    @Mock
    PersonModel personModel;

    @Mock
    List mockList;

    @Mock
    Player player;

    @InjectMocks
    Game game;

    @Test
    void testEngine() {
        Mockito.when(engineModel.run()).thenReturn("Engine is getting started");
        assertEquals("Engine is getting started", engineModel.run());
    }

    @Test
    void testPerson() {

        Mockito.when(personModel.getName()).thenReturn("David");
        Mockito.when(personModel.getAge()).thenReturn(28);

        System.out.println("Name => " + personModel.getName());
        System.out.println("Age => " + personModel.getAge());

        assertNotNull(personModel);
        assertEquals("David", personModel.getName());
        assertEquals(28, personModel.getAge());

    }

    @Test
    void testMockList() {

        Mockito.when(mockList.get(0)).thenReturn("two");
        Mockito.when(mockList.get(1)).thenReturn("three");
        Mockito.when(mockList.size()).thenReturn(10);

        System.out.println(mockList.get(0));
        System.out.println(mockList.size());

        assertEquals("two", mockList.get(0));
        assertEquals("three", mockList.get(1));
        assertEquals(10, mockList.size());
    }

    //https://stackoverflow.com/questions/16467685/difference-between-mock-and-injectmocks
    @Test
    void testAttackWithSword() throws Exception {
        Mockito.when(player.getWeapon()).thenReturn("Blade of Chaos");
        assertEquals("Player attack with => Blade of Chaos", game.attack());
    }
}
