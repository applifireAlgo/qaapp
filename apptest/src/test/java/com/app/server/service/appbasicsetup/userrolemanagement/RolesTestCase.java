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
import com.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleIcon("qvgirvFvqAnzRuWIWZno3eKTlucmkxAFVujF8VKeKpFYoL5Sf7");
        roles.setRoleName("N2N0yLSpIFEQONd3Q49pJ4NfdWSMTnzkZiCM0YTsLqGEXK0fuH");
        roles.setRoleHelp("NiAF33ePRBM3k09kIqaGNUMqJNvSLN2INaEjX6ir5QU39e0Zpm");
        roles.setRoleDescription("y1eExOUkG2rQVwXPjKUuF1Wz90BLDWaMCtdTMVPDnQqMZPwoDW");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuAccessRights(10);
        appmenus.setAppType(2);
        appmenus.setMenuCommands("oUag3hsaP9NAvvSQv7RSNds8Rhw9A1dBhIWpAsSHesCtwTluN2");
        appmenus.setAutoSave(true);
        appmenus.setUiType("BK5");
        appmenus.setRefObjectId("PdUd2GAnv1brzLQ9iYLfPPODxIogy7kPcdMNkGkXaK4HHQs6Ya");
        appmenus.setMenuHead(true);
        appmenus.setAppId("OT1XHagE8MxafpboAopyOYsig4XYuvxtE6RIq0b9xYTkW4bUAt");
        appmenus.setMenuIcon("Ze8RIjWk4eZbfaNhlvk7ZlT5rce3Ij0vv92qBGKn6EDRKTx1jn");
        appmenus.setMenuTreeId("tRbMUKPlWUeUTYQgH9vAVI4HWH8mIfKKFMHjhM6pYKo5XbkwEA");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuLabel("OqQNm0xKOat4hnlyhZWwTzzFz6hPG44pxTDPPfzTlakmgpcMmP");
        appmenus.setMenuAction("koeEptdmV1fWfhGutrLhRGTMYUbix6T1UnFMFjSHFurrcuVapF");
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setIsRead(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleIcon("abMUKkzjc3NsoXy2LwvRoSIc0g3UnzhaAAURXqy0JhvSzp4Ob1");
            roles.setVersionId(1);
            roles.setRoleName("WH0F0gjL6Bt5hvFgpcZQnSmfQuUY6gHlrBh0iXLkEv5F9J5BAM");
            roles.setRoleHelp("WcYZ6dww4gi6Mhbzbs90aqdX9Dd5auSik2DwM82aubonfAZ60c");
            roles.setRoleDescription("ra1X1JkmHOoi86xJzciZKBMewC2BCbTEG119bV02dsNeSlIxgs");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "Qo7LCBKkdU1StwOAiyixFfhEMZlPYrjPeUrQFKOI3FRWCGfmCm9ftqd53ndyhdglGCx9csLwdUrTcIxqiRdDqtWGrxxSZHMfe8PNHDghteUMbw2jD5mTesjONyBxbx989ZnG8FFCknBVCY0gxk1wThcfhi0eqfIfi8sVhLrWxZTZMVHXLeOOhEU8WOh7QqJDFfu11Fc2W9jFNxcRgL4B847seNdaPV4DydJaJ7qQB0lBLtTzc7c2M6X6jdRRt0BRk"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "j0zpVgVpN6Ds9jF3MM9FkBtTpUcPrL08fQGVSIEBlEAnfKDoIBweAXmmopOwAapTyjEgMZyDyGcvofMAZPJZ2y9gyh90LZbvL5FgBfKkSId2ykEZ4YC8ECVwsDB3J13KsF7ZY8VDmbFr7tOT5XtCDrRbnpkfymv1oVPffWPEmUgIjbVbC9WvFfO8RqARHT0RAdxEej7HqiqNimX2OxQhEwJbfu0qQXpyp4Nma1qXIs6pPxBbqMbCTEyMSCYuhgfEk"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "sHvZadVjzYOt2JSp9vmEoMjhw5hTE9iL4Bd7nb5yQaJTDzrd9gru2SLSTUiuN2v8a"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "9qDyW3wwmt04Er9RkwxVBNiT26gbcwU5HiR0BKToELjx3XsiCNtJ0QyD4jR1UBojiLRH7VcF9Q1hy2OvKehqHM6Ru3Rafyd9mo3Z7CK8zTPSLy8T6sAnVh7YttdeF68aFrYN2ePfkFV3szvSYR7mJBBNb3ABXytj1uxrL2gNk7f4lLiLwh36KWTHsnK9JIUB0eTSyMoOjKqvKnt4zPxCfeZlL6jvswoH9AaJEd1dL3YPOYwAPokyV2HSwn3ZIUEko"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
