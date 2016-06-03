package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuAccessRights(9);
        appmenus.setAppType(1);
        appmenus.setMenuCommands("oc9ekkYrrV561X7gHVsmeXlxQqUEUyvSjTpL79WwTwBZvEjDlu");
        appmenus.setAutoSave(true);
        appmenus.setUiType("rZb");
        appmenus.setRefObjectId("JiCkuYy58KYQZD41fEPRN029PakvuXa4Yqvzr6RpFQAucHCjGA");
        appmenus.setMenuHead(true);
        appmenus.setAppId("FwFEkw0hYmRE5Kf8ozlUX7CETwe218xPjfGpNjerpsQm8XhTEz");
        appmenus.setMenuIcon("PdgLuSy7O5tYNE1O3bakmEW4jHLMuA0Rev4OiMGgWcT6h6rPug");
        appmenus.setMenuTreeId("cFlhyeoc7WfU4L8273i9Dl9lPS8OQVsy7aEABQLWtl5D0abPCG");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuLabel("jI9kEHTSmreJzTZRHMM5WLM2btfsQWXDGX9aca8guETOoq9Iru");
        appmenus.setMenuAction("ouW900mTdv7iXU5sH5H9ICTePyIbGvzKxIajzdAhqek2Itp25r");
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setVersionId(1);
            appmenus.setMenuAccessRights(4);
            appmenus.setAppType(1);
            appmenus.setMenuCommands("oGr6WyFofYX0UKA0w3dHkXqKDMO91y3rt13CWDCKj2Dz9bG7nL");
            appmenus.setUiType("eml");
            appmenus.setRefObjectId("ZHI2swlU1IxqrrgOKzZMLBzQEyO5Zx0oiFfLQuPt13GdHDgslE");
            appmenus.setAppId("NAjch2jKs15BTu4YpfZiMdqdvHz92ZvuXWuqpUVZcd5pTtv0b2");
            appmenus.setMenuIcon("REt9F09KLkwodY2g456rjGQ0H330xbVU4iMNtznRC8SdLmVM9Q");
            appmenus.setMenuTreeId("yS5tBXu1oRTreRmNCepzM08BQkRrNArVshff14TxIMwcJ1zs9d");
            appmenus.setMenuLabel("3GX9nOu8IwZl0XEtogtdn0GtbafN5bb2VzzLuF1xLssrG431zT");
            appmenus.setMenuAction("BN3PiOMv85TWo6KVlpQqVu3pfif6xwiqYG8bXidZPP7t0lIg4F");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "DMT2eVLcDmC1g52OisB1X5fdvwBomFRSf5856IJrDXwbRKSZsafK9bFJiyDlZNVTJrIwanaozyZbtaYNO5nW0fOf9VCWdBtI9uOvFUiK4u57wqtQURpTY6Vk4zSuNP56j1l4PXiB0GkIuVUHLVxO7lfirdZpn74PMBjMVv0y9LqjVH2G3ob3gJZBIN9UmVyUrX6iflXEqZTk7Ny5R8Xe20QGGOcBPuuhUvs8nipbvuUcw2tazbVXx7h46kmwwcI4J"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "Q3drqBSlbdAM8AzvTpGlrfdo9NDl7R2jlK6bCzhwdt34jpFWkbYdijIqJItty29sii83R1Z6QcEa8JABxxra3ctbMzYVQQvZHTkkdk9YXHkR9oSwCXmAjIorVRHvE6wNqY59Bvm7Jq2gufbpJKmeMqKc3JTybpuV7axlGvcpPkoHZiXbzgw5l2dsed5C7svI7VPC5XmIpaXYwGI8QgBpRnyHsmDxiGlztK4CiQWjJGOixpWVdfLwmNut3tsL3iPrJ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "ndhj1cIPzyQlOeaMvfvHVGaBYFbPlbbaG7QRA4gok8IvhG7n1CZ1CR8R5xeJkTInO0m41yogRsZFdylconwXZHzdsxLclOQDvRUUzlBzePeWDbDBEpZF6JBKRWFCItSeMN1LfXMc6yxA4fRh0KnILr2cdXaKOclWrRHbzkg3W8QWxA9lpHAtdtnzHChX1Ye3sumCAwKQcaU7d40pZ984VJoYF0Ix0B5A1Y2a63zAzcenhjS3yJyq1rRNxqmWY0WFo"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "Wo3PaC7K7OVw103R2Z0jAtWxiqTpn72Ri5jsLbHru34JYMt5JZ8qoNbGVSeWNSEbq"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "C4m0LxX0ep4TyQE9pLbBXSs17B9DMvCcdYQXMTj4prmW3IrkeCHjxKjcF0bLDABU6VSCeTvguOvPbeT6cH0i02su4bP9eBQl35oFYTSxggUidWMh59RB2eRhgouYZfJHQasXlUYsg2dv8knEQlOALGNSJbj3EvpLFuamvKH8wRr5mcinkNwFuA8r78e8Mm485E5cIVnMVC8exAYzXsjuyAMvzipSnUHaSrY9LUm76EDeV4XJjTm37JF1oxvX0fbk5"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "L4dU"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "ReQhNGPJ4mgZBKFDhtaztsIsomH7jmXNuVhSEgWyT9L2FMQxoYx6NwjmzCsK9fhtKPL8Qw9ei2V3b3rNrgUFIgplIVY1OvlDjcpCvjjt1beABwtmat428UPkIZo6xznhVGPKlS3nS2f9Iy5aGsgYUXJ96Gvp1oNzvjKYRYhviHJ5xEkrp5r4TLXqpIrexBWq3aZn5Opeayu3vr0YfJWPkYUTI9Xlf8QAJBFCT2OQ0VxiHDUPCE7GzJQ2MjQoXGbjJ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 14));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "1NfvPa5hWKC9OIl4mH7twrBSTnO6VHCkQK9zdhSiY57BBcpUGAG1JT6Zu5KZO6stDpT3RXuEt9nKAhvpg4BtFSedo23lulbjeUZdxk1JqVV7DRFRXF94rm5Kqzt54NHaVsKZKANB57zRMPVyu39HnnpGWh65xU15w0236oWARV5jkIEezR6gTajX3ZwO7uTx0rBdEmlDcvhXHzj5wfIGO3fkJQg5wPmw4c3fgEVEhaSiJzs8DUHIqCT7RQNwHkCo2"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
