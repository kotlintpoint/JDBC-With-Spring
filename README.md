# JDBC-With-Spring

- If your DAO class implements any interface and also extends HibernateDaoSupport class then Error like BeanNotOfRequiredTypeException 
- Solve error with following change in Spring.xml file 
```
<tx:annotation-driven proxy-target-class="true"  transaction-manager="transactionManager"/>
```
