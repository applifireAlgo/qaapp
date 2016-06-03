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
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("Vqgu4yvXl87H0JxaH1Ja6ke3FodcZtlwk3o78hQHxCamDZ2Wgn");
        useraccessdomain.setDomainIcon("6CSSaPWaepvVzaOZ0DdzGjjYKgKULHjomLPlXQPgrIN3kGps7P");
        useraccessdomain.setDomainHelp("cchG3EaeHYocZsdYf3TRvdRQcz46TZu8LonKUC4FCc39nF4p4f");
        useraccessdomain.setDomainDescription("KUuoTROSFGPXYJf8y0DoEnTeGk8SwzVDrQFXrsfbQ0V2J1NxZg");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainName("Q0ZeYtYPFFJYMn2P9HfkTO5HkV4TvzygLRPNZdzkMfKWxQJYBv");
            useraccessdomain.setDomainIcon("2oydMcduDhvU5m8Oo4Q6v5CusSpfDyWWG9P3tKONJS0SV82rOf");
            useraccessdomain.setDomainHelp("0RxQodrl0AslOppE0mnG03LgiaGU2GmVvRPbXfc8iN4pwFB8t2");
            useraccessdomain.setDomainDescription("yonJJMb57IqL9anMJYyW0EjTsAhrk08ogC77aoBRCsWagLlmym");
            useraccessdomain.setUserAccessDomain(28792);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 188294));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "1FESf6y8wRoHY7OWmo1PKmna57f8HGyktuo5wTmUU3BUdJmb6FBnUVtwDvR3s8buLUxRucxzUP5CUQEpuqcdI9nibMpXYB8xoREAWHhzUTqZc3RtKSNT4qUMWVXI1LDJhoZBIZkaZTYgsMDHRzTMSwzk4FyElGAlVah6Y2ocmibtNCseQsOrMxRpQQ5GJ1RxMBdQ6TwObkZr3UTEy6rJiyJLF3XR5grSHktfKH8EsmOMr1e5AOVndQJLUz45CoCOj"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "XkoXKEZSx8zN6ZxCxiLb5PBm0wkY2YvsavhnebDPDMifoFU7oTdSNOsAOrhJgoxS2YzyPHjKpcC3GumuQTI8Qi27qmyK20y4kYtGiDNeYcnsz2P4e3Grj0JHn9tBu46wtMU20RtMapgQ0EdAiX7t5WPR7IRA53xfXnz8vlxeraUVRYKym9MBuYTuOseXkvDuKZBJv3b1aUyF1MorjdPHqb6QJuLCfuY79nHEGPzJdPNwHoXE3ax7oXjMtVYToDVVn"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "8qy8xmmlHxZlwDGzkPBsKfDeej7JCEHkHPuOaZ0nd4vxVnReBZpNzeTvL7bgsujjlnppCF62jVQwgBr9NCEGSMHe4wF91eUA3S0yuaBpusqpnetDWCx38oyyUIvlcTovL6vvMbJN2Xb6FV9fbRcqDZ11YsL0H3bGYHIICl7bOOkF9pgxdHfFwreJ5dQy9xQxizY2slqfitBntRxwNqV5B3vKw9hAJAk2Qo1llIOodx4asCgjUo7o1kObW3caXdDZa"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
