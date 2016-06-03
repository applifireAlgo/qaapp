package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import com.app.shared.organization.contactmanagement.CommunicationType;
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
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CommunicationTypeTestCase extends EntityTestCriteria {

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

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

    private CommunicationType createCommunicationType(Boolean isSave) throws Exception {
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("MdEDulUHpQlhPMw3e1dr59QQmJuIs13SqAC8Xq4vcujZzPJAzv");
        communicationgroup.setCommGroupDescription("O1FdNGlJVW1Vcb9liZubnTVQmV5pX7Ql3IjsDv88LcBgoxjLqc");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("g9eMDxIopyqHS3nIpw1Szjv3R9iOj4Q1lV7MmpNNioDwzVtzTW");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey());
        communicationtype.setCommTypeDescription("bftV9vFdRiiQ3jHuKRxvOThFcNasPZTROIRFihcyirHAnJW0ZL");
        communicationtype.setEntityValidator(entityValidator);
        return communicationtype;
    }

    @Test
    public void test1Save() {
        try {
            CommunicationType communicationtype = createCommunicationType(true);
            communicationtype.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            communicationtype.isValid();
            communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CommunicationTypePrimaryKey"));
            CommunicationType communicationtype = communicationtypeRepository.findById((java.lang.String) map.get("CommunicationTypePrimaryKey"));
            communicationtype.setCommTypeName("5U5EQ3hB6dlbebDHh5ArHHbgtp9JhFWpYJMyZJ97wao37NCW07");
            communicationtype.setVersionId(1);
            communicationtype.setCommTypeDescription("ggkskCLzrTwBqELm3LvSJ8acEt7zPVxuJa2stTOj5LyPmZodCg");
            communicationtype.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            communicationtypeRepository.update(communicationtype);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CommunicationTypePrimaryKey"));
            communicationtypeRepository.findById((java.lang.String) map.get("CommunicationTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CommunicationTypePrimaryKey"));
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCommunicationType(EntityTestCriteria contraints, CommunicationType communicationtype) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            communicationtype.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            communicationtype.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            communicationtype.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            communicationtypeRepository.save(communicationtype);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "commTypeName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "commTypeName", "ImnZsTBjo2d9zGk06JZAYbnVQVFN7gyTHVu1Bpbz7rmTFB31QkGkyaHFqjFD9JrmCQ1wThAHQwEEthxd8R8cMXCdTerFyoPxbsGRWeN5RQwhgBkkajlxcSar1Bze2gEwG"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "commTypeDescription", "xEEmudFvbVh6St83D5WoXR1uuIHvZVvVaIZ1VUsoLppd083gP04imKQmsPfdBosoUY7aUj7tqxsYSZRLY0kxUNrJ8wgiANzMtf6dxXW64OPx6v26EzJPOYsAKdfZVfEITAWiCjmITVYFIqPMhhxAUCidnoa6Bcz4xfAfgxLcebvzLeJhwMJGijJ3fXA3GkTcid5W2rkY30z5AnCO06pLEl97UI6mDKKbE3gxCRF3hiCHFyFWYbSOPRlo4Lp7kEDPg"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CommunicationType communicationtype = createCommunicationType(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = communicationtype.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(communicationtype, null);
                        validateCommunicationType(contraints, communicationtype);
                        failureCount++;
                        break;
                    case 2:
                        communicationtype.setCommTypeName(contraints.getNegativeValue().toString());
                        validateCommunicationType(contraints, communicationtype);
                        failureCount++;
                        break;
                    case 3:
                        communicationtype.setCommTypeDescription(contraints.getNegativeValue().toString());
                        validateCommunicationType(contraints, communicationtype);
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
