package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLongitude(1);
        country.setCurrencySymbol("pF5G9ufaX3TC6xNgVvWEtrMuPJ6Ho0LL");
        country.setCurrencyCode("k6B");
        country.setCapital("qeyDDqQuxnnPNgTxBCuLYPZ2jW3K6kBx");
        country.setCountryName("VZXdJHQKGRuSJwSfs92ugcUWRKTordQKmRNBnMyDQz5asFtU0W");
        country.setCurrencyName("idRDJfUVPXevMiyJhnJ52n8ihIRUsgz0w54r7I3dltEmIM1ADH");
        country.setCountryFlag("3SlJ1LkncNYDPvI3kg24SrtzgFG2DXHT6z6WJd2GJne93USrtU");
        country.setCountryCode2("5Tf");
        country.setCountryCode1("Yw5");
        country.setIsoNumeric(84);
        country.setCapitalLatitude(4);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateDescription("gcdUzLnnYiM8HH8MJYLJaZQ1jj05jjEcK0wJHh9OzBCpqsio3Y");
        state.setStateName("M8ZfFIYhvHlqsX4nWGnEW216b2wcGhi9HCQfWGtXJAiIdCoVmJ");
        state.setStateCodeChar2("BJqR9n76yrYKa8TLXeDmcODIhJBjvfbB");
        state.setStateCodeChar3("zkrGHEDV9YOoWpoxYZbT7UDLSiAw9Zk9");
        state.setStateCode(2);
        state.setStateCapitalLongitude(10);
        state.setStateCapital("RKbmD4qOmBqcvrEUStAopISYlwFQ0mZnxvAvKFBuh08arBBa3B");
        state.setStateFlag("j6MYTORkUdN7T4nSEH8WbFEpjhPDj0MekkwP7pHZnp68ToDLEE");
        state.setStateCapitalLatitude(9);
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateDescription("gXgLiLPlWxR87ZaR9f3eMJu0kZ25Xti96lXzK46WblJrLnqGGh");
            state.setStateName("oaLgF2z2dhD5KlIGF4T63nC9CQ5tBkihubONWJZpcUXKoaq6Pw");
            state.setStateCodeChar2("eWh61VFp3lmXBpwhcMouek8CEieU6V2u");
            state.setStateCodeChar3("b7WA7XyTeW921HM5aDp9u5kaz5tCFoQ3");
            state.setStateCode(1);
            state.setStateCapitalLongitude(6);
            state.setStateCapital("5AF4UdStfs7zwVSmCdPoo0bVccb7YIOzDua0oLo3TGqkMFEaVo");
            state.setStateFlag("KWpVMzEUC8xPbupuC8VIvl0okIplsW83Ath57OBdmSeDsnkHhg");
            state.setVersionId(1);
            state.setStateCapitalLatitude(6);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "5NoMtJ5g6zihFNc7hBbkon7idBAlRSS9tgmtrJvOUfbVjJwc0xJhyln6o07QmASuaCKCusxYbPK8PRcyrma5gqrTy4KpezL1mY4hnNn3T5LvZXKN6cxz9LhfreL1wk541W99VMrPInqXk02MPfME1WP0WpC8oh1NndgrsF5V8882TNaReqWaEMSkrPrYXhYZEBw59rrJQEr2ep5eOzKPrxfYCD6Ay221TiLur96FhA6Sef7DGFHoZsgTsT6t21cn7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "uA3UfIaZuSdABWtFiqOBViTOivB9c2pwb"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "9MXytSWlxqX74fRAGSu5fRLYQupfJ00bu"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "DRCZ0cF679mp9DnsqORd9SGwNm3pxrW3fYEkCrWToIRA20LwMaChP8shBTgje92DFP9EQ2Wi4irlYSuDNgDJ6sQmlMEsJZlqmzOqAb96G4cupPWmWVtaijQrX5gcofJfirtOMMPEFoj5Gz8jT21ZOGeYnfrXwdcGbnJKfIsMf6MmvaMDMdgJkPIe8lPy6U20y117OpS92BISj7v1yALf8SyUhe458OKI9z6IgbBmpUzz1wZs4p3DaDamqXRCYZW87"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "sKb6RnUPa6uWAi7bw1mOYkRjIYjZ0bvXO3VOtccqmWMbZZxHaF47dDQUdToxlBYLXums3DxqEK0l7CXmwM1Mgl6rzZtYW7wOUrypw1dE8pJWjozHVgt8k7WqVcSOKXgTc"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "Y8byMJjFQrgxCkpCYXfbV3akfinTX6cUHqkDr2JTCHRh4jzPeLwnstShw3t5tfd3KxhcQ7tqazTvodDSbyut0H7OFtDNQS9tkmm1rtAMDSAZILXLu72hGCzgP7bljORFX"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 15));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 19));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
