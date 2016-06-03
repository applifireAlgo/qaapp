package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("gnNrwdjUu8s519VQIi6VkoGJdT0uUGCr7BRa87VU2KfIdupo6s");
        useraccesslevel.setLevelHelp("8RTjIdPWJoicC9a00VjnNT1nvXJtJTQStGh5kdmhlqbzN9RV10");
        useraccesslevel.setLevelName("LW5GzhGBKUqEnahD4enxuoT24MHu6WlPRb4QeqpETSXfpxbKbz");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("7RBrIsstGmnXNF8TwvN8TgQuX2EYZ5aoYAaMwyarTLUgbXanfF");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelIcon("E7ngM6HjPXdHHm6u9PUwG0PGquUBcUCchrQzEuHN6RZ5oxjmAG");
            useraccesslevel.setLevelHelp("RpDn09583AlF97nuZhMZKnS1WX9YiTILqnmtBLz6B9Mq35yqYz");
            useraccesslevel.setLevelName("TMQdB5LJCxVbbgONbTAwjLRcc4vamhkxQGndB6vafbQUVBUyvx");
            useraccesslevel.setUserAccessLevel(86808);
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelDescription("M63N1f3TLqx0U25JmnKw5TerjOhxp03JZI66kDDkTsKbMphcz4");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 134763));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "Y5OVZgQufSfCKvugY8rh7fyMBPSs3Z2369nWmVmnT1CNs7exdXRVKxuGaer5jXhwIuwmpiDLAxUppElw2I9k3eBtszXduUalJUWSCJ1YE0sqn33DLLki7FDqPY1bfPTtJJ6JfZYRvdipNDFmeRuhYYCBFJYJtjgZ0t7dCK4qNHbfvhddBF0ZDAQYFiLOE9Ut9WEeeQHNjgwghEXB2VqYCeBc1dwIvmblew12dGSz5QCwkCVzcWU4qT2KJQoizZJVn"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "jxfRqzdTn6olyL7WOQVAeHcEL5PY9EyclVx5FmRWjnFJxXb7zDxItykuEVv7vAlsIwBMdG78Yx5wAvzODmKUc8D9Rcn2KuwnEt68Ums4qrDvHicQIEzWkRXEqtdEoIJjws6SdoOULEerVO9WTGwAnQynRMVxbYpTEMP9Gnc4d3UoYFmbIzgI9Rj6q3PLIgQF7ofstNGULVwBp3jLgaGwjowCP51wNkYhMdi4FWMQRcwJRePgc5MCNprH9JRNG1dQx"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "fiiCyuIgCnPgzOghpLWq3ieyJWFfCws3UiG6cwtIKBM3tTxLygST8papSiy6INYJKw1A03B0QgdCd8uyrkfcvehQHUxCDyC1xp19wAzHSbHhjUTHUFG3FRaYQrJf2bWggqfgivnPJqGN2dcoKGZUgEum5YTqQQgLFyQE6eD5KKV05LjOZEm926tduXLhZRQEqpbVsslvjScjMr8KuuWmA357PNnevrmGgG4iMrRXwuuPlVm1062iPdIJBvIjN0yCS"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
