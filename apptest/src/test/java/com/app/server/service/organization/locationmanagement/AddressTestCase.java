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
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        State state = new State();
        Country country = new Country();
        country.setCapitalLongitude(8);
        country.setCurrencySymbol("1JytPDGkOrFOdysZ4Qvd1GwPgnssu31Q");
        country.setCurrencyCode("Fw4");
        country.setCapital("UjvYJt6Pq31eVma2zXBeS6uU29ChjeFO");
        country.setCountryName("JkbVgKAbWSlBBd7NtYm87zGqYvJPZKokTWSivm4fe8GKFjlXcL");
        country.setCurrencyName("am3IGQ5TPiYmSWiwsAsTgHq8xRl6Rlojl3canIuYKmuyosUkNf");
        country.setCountryFlag("8UJIdXjASUlDOTPgqxGMTWyrDlwhsxjOB7Y54AkIURbnp28yFs");
        country.setCountryCode2("kcl");
        country.setCountryCode1("x87");
        country.setIsoNumeric(261);
        country.setCapitalLatitude(3);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("tLH0AOiTt0U75ZPzL1VyDrYURulYN2Vyqq40gY0eh3oc5U67yo");
        state.setStateName("eLX4FBrXU8ABjw3vSJkQZET2CgPCOmF0sqCOCp176mxS3hVoCL");
        state.setStateCodeChar2("JnMp1v32UqWKsTC2DtFk9YvWKcoBM6fX");
        state.setStateCodeChar3("iMLU6G2TQLhB3Xcgv0cvIEqbnNWSExmi");
        state.setStateCode(2);
        state.setStateCapitalLongitude(6);
        state.setStateCapital("iUwmUhG14ORK9i5oUldVYn3PFgueOLvX3oww10AiNvvXsMsugn");
        state.setStateFlag("Sa39K3JCJ1vKJgE7T91Dz3fcRQK4JFUn2Q2ys4AaRa5bhdYYg2");
        state.setStateCapitalLatitude(8);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("aZqmQOUc4iElzQZQEntdNhsQyBTdgSS3fkNO3uP4MPIsp1wyfV");
        addresstype.setAddressTypeIcon("iDgXXx5L59GvdJ08B0wm0yxBNHV1VRvI1bco3nYLxCWuvdURYr");
        addresstype.setAddressTypeDesc("32Z1AYYuQN0qUaZ0kQUfSFfHIDxhEWIGOGg0kDOSlm2aBcYhJ5");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityFlag("xAD88yWKaAk0uAZcWeu0rHwllNoyiRN93LS5JqoMU17Lz4Y71P");
        city.setCityCodeChar2("4cQj9AFCfDknVAb5KblceyR2ehLq2aIr");
        city.setCityLongitude(6);
        city.setCityName("NRdlrv7mvUaMYOGDXcNHu2UL8XMNsRKDGIbVyYYr6iifbHaRxL");
        city.setCityDescription("pU4H3nD1HyK9tCBK3qW5kS6AaO0tXRpdAS6aga151j6Nq00nXi");
        city.setCityFlag("I2hBSkI5jK0flUNaWRZMTUtRmL3W9vwcOeY712mH79zIiFM5rC");
        city.setCityCodeChar2("J7F58GezAp8zjcyCx2znOzedA6pqu2PO");
        city.setCityLongitude(3);
        city.setCityName("Oe8oADpaixEW7LWisu9dQvweWaDn6CoBCb9Ep8rCxrFgUdgcYp");
        city.setCityDescription("QLqoZmvhiSluUNI9uLKMOLbjalIJgznZnqqMLzblfZDHnpYIuP");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(1);
        city.setCityLatitude(7);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Address address = new Address();
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("jyz1ZSPywChsBu9qXw8iZJ8DR4zvfrVAuJVwMM35wtTX0JPCMM");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("LlicwJoWzDTsViTOCTbYX7OxOpTRcq2yc1b3lfGUJfUzUcaNz6");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("5YI3VZfgj7RXtAzheO7Hux0XFnubqs3LmM2DDh38ozm9d3QPcv");
        address.setLatitude("YUvbygJRsL9nNKzGISoAGrq8q5evqXMFdCrrfoBsq1HuxeROob");
        address.setAddress1("EtYV2qMsUtB74x2SYQnq3QtLYMhI2nU33dv2ndyJt5jkty2Wkk");
        address.setCityId((java.lang.String) CityTest._getPrimarykey());
        address.setAddressLabel("DQcgqJMyLCq");
        address.setZipcode("kXwRAw");
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setLongitude("eS6IjGa3DiaWy0ouvn0Gxp80pB7h8F6dtsUhkDvWRb6dUfiHWC");
            address.setAddress3("zCNwqvxxQ79beZakFIK7yY3MFTmiHxMVJ53rYKlw35XOBONAAd");
            address.setAddress2("DtRcaZe4NeHXKQqrf40iGCXtkVU9zU5zPv5AUKeHbOOSzphi1M");
            address.setLatitude("b4zL1idRfFxaQKASknQcL26qo286nypyAR1CpiUDa1SK2rkZ3G");
            address.setAddress1("zpjr6fm00w4pFAeyLzNjQqIxuxfihJB24Tb08s8xQZ5cFhFXW4");
            address.setVersionId(1);
            address.setAddressLabel("6a1kCDl1OLa");
            address.setZipcode("hQedEh");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "CsrtnMBmtVaN"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "ae7vPaiyDmij02cogQARejx0tHmp0K86mgkaeT5LEzEurtaQZW84pEgru"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "BWtO2uVi4iXo9BT8kFaDogrxSiOllJEqXDATYBOPdXgxLESCNQqdl3Nxy"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "qnZ9AmUNMdE8Ft2pbk1dFC0INclWgBsyGvupZ2BkR4NRFeJJqatbcOJc6"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "CE2oOyf"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "oiWPv6pJaDMpmezsqqF7i7G5LdTJX8WUAypPsdDzh9Vx3hNXKi99mMLJEt7K2JtUc"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "KJvUWw0oeekvgjBUCVrQ8nzmho72cGLTqMde3M4qhS2OHSDC16F5ZjtwfOu4y1s7k"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
