案例上手 Spring 全家桶



## 1. Spring 框架——Java 开发行业的标准



## 2. Spring 的基本应用——IoC 和 AOP



## 3. 掌握 Spring IoC 两种常规操作，提高工作效率



## 4. Spring IoC 工厂方法 + 自动装载

**工厂方法**

* 静态工厂

  ```java
  // 1. 工厂类
  package com.shicy.spring.demo004;
  import java.util.Map;
  import java.util.concurrent.ConcurrentHashMap;
  public class CarStaticFactory {
  	private static Map<Integer,Car> map = new ConcurrentHashMap<>();
  	public synchronized static Car getCardByStatic(Integer id) {
  		Car car = map.get(id);
  		if(car==null) {
  			car = new Car();
  			car.setBrand("brand"+id);
  			car.setNums(id);
  			map.put(id, car);
  			System.out.println("build card, car = " + car.toString());
  		}
  		return car;
  	}
  }
  
  // 2. xml 引入
   <bean id="car1" class="com.shicy.spring.demo004.CarStaticFactory" factory-method="getCardByStatic">
     		<constructor-arg value="1"></constructor-arg>
   </bean>
  
  ```

  

* 动态工厂

  ```java
  // 1. 工厂类
  package com.shicy.spring.demo004;
  
  import java.util.Map;
  import java.util.concurrent.ConcurrentHashMap;
  public class CarStaticFactory {
  	private static Map<Integer,Car> map = new ConcurrentHashMap<>();
  	public synchronized  Car getCardByInstance(Integer id) {
  		Car car = map.get(id);
  		if(car==null) {
  			car = new Car();
  			car.setBrand("brand"+id);
  			car.setNums(id);
  			map.put(id, car);
  		}
  		return car;
  	}
  }
  
  // 2. xml 引入
    <!-- 1. 构造工厂类 -->
     <bean id="myFactory" class="com.shicy.spring.demo004.CarStaticFactory"/>
     
      <!-- 2. 使用工厂类构造类 -->
     <bean id="car1" factory-bean="myFactory" factory-method="getCardByInstance">
     		<constructor-arg value="1"></constructor-arg>
     </bean>
     
  
  ```

**自动装载**

* byName 装载
* byType 装载





## 5. Spring IoC 基于注解的开发



## 6. Spring IoC 底层实现



## 7. Spring 的另一个核心机制 AOP



## 8. 快速搭建第一个 Spring MVC 项目



## 9. 深入探究底层原理，应用更加得心应手



## 10. 一文学会 Spring MVC 的常用注解