package edu.neu.ccs.cs5004.assignment9;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class GeneratorRunnableTest {
  GeneratorRunnable test1;
  GeneratorRunnable test2;
  GeneratorRunnable test3;
  List<String> list1;
  List<String> list2;
  List<String> list3;
  Map<String,String> map1;
  Map<String,String> map2;
  Map<String,String> map3;

  @Before
  public void setUp() throws Exception {
    list1 = new ArrayList<>();
    list2 = new ArrayList<>();
    list3 = new ArrayList<>();
    map1 = new HashMap<>();
    map2 = new HashMap<>();
    map3 = new HashMap<>();
    list1.add("123");
    list2.add("456");
    list3.add("123");
    map1.put("123","321");
    map2.put("321","123");
    map3.put("123","321");

    test1 = new GeneratorRunnable(list1,map1,"first","last","NO");
    test2 = new GeneratorRunnable(list2,map2,"first","last","NO");
    test3 = new GeneratorRunnable(list3,map3,"first","last","NO");
  }

  @Test
  public void equals1() {
    assertEquals(test1,test3);
    assertNotEquals(test1,test2);
    assertEquals(test1,test1);
    assertNotEquals(test1,"LOL");
  }

  @Test
  public void hashCode1() {
    assertEquals(test1.hashCode(),test3.hashCode());
    assertNotEquals(test1.hashCode(),test2.hashCode());
    assertEquals(test1.hashCode(),test1.hashCode());
    assertNotEquals(test1.hashCode(),"LOL".hashCode());
  }

  @Test
  public void toString1() {
    assertEquals("GeneratorRunnable{placeHolder=[123], inValues={123=321}, firstName='first', lastName='last', dir='NO'}",test1.toString());
  }
}