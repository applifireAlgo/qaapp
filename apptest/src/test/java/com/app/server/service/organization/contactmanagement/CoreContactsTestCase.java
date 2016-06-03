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
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
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
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.app.shared.organization.contactmanagement.CommunicationType;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Gender gender = new Gender();
        gender.setGender("h4jPfQt5ZvYclnoNieeN1awIn4DOGb5Pjm8Hl9XBUQvUsCjx18");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguageIcon("r5A0C3gJKrkIav2irL98vKr2YRWIeUlyi2uTX158uIv7CsNKFv");
        language.setAlpha4("KwtR");
        language.setLanguage("BJG188dOO2V1s5nrSZq3VLuqgIUcuvPQVjIy0IyIFzC8atEEcC");
        language.setAlpha2("Nn");
        language.setLanguageDescription("p9SPcEfp9NxfQUi9YQsxaPSdoXcPXwp1LvLNfPAt1ibLmMEdpa");
        language.setLanguageType("WDPCcfU0lIRqMXmm7Og2YwoZxcapiz6n");
        language.setAlpha4parentid(6);
        language.setAlpha3("RPC");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("eELiPAk5QDPD161EfQdMoyrSoY47Hsl6Z2EmUwMR4jAQjLcApb");
        timezone.setCountry("zc6J3RCD7B4b05S2bN6BswGSlShpCD0WHBMbTI6NBKMzMuRgxB");
        timezone.setTimeZoneLabel("OAj306qlfiN75aLRcFHQEMgUno3yJwntQ59K81LS1Cl9zSwcEX");
        timezone.setCities("vjSbBIKax4pdxB6nkbP5r5gK2CoNRU9hVkU4bz9ycWXnEY2P2o");
        timezone.setUtcdifference(11);
        Title title = new Title();
        title.setTitles("te7xmMkJthtdEQSCXVlPMVroTJHypuLFETPt6CslqNEBaqSXv2");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setEmailId("dLWC0BWBXhVXiaCzcFjtIMkHoOAwFAtWBGzBHv6erWOvi9XosJ");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeMiddleName("NI9NJJkQFlHfI6apLi9iuTq3wWV5NYDJwgJ3JMmlVeBN6ppZ94");
        corecontacts.setAge(29);
        corecontacts.setNativeTitle("k7liNS4EiTMPb8O8Wlj7T6Uh1hGBed2TmGZX4cvok6qlTLbO7c");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("32akq8UgQTQ36mS48eYkyGJr5u871AhZlPyYhTZySR26DUCWtE");
        corecontacts.setPhoneNumber("QXkjeKNMVsJgXdogsGST");
        corecontacts.setMiddleName("l4NHGV90inz1M5JSqcvAdybk6aZb3R1CFW8407JoVm7WjxNFTm");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setLastName("iHYehlbjflTyXRboMrpbViYrcQiTCidr3vp7mXwrSzzePFHWQX");
        corecontacts.setNativeFirstName("Ys2nzYBzIYHixTjlMgzweO3sSyNKNB9ff7shyOuForCpcqsWon");
        corecontacts.setNativeLastName("GL3zD6zRL0xec6ZOcClmjyRN87oFlX71y5JMX68jdAF6MP8HnG");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1464944121918l));
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1464944122004l));
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        State state = new State();
        Country country = new Country();
        country.setCapitalLongitude(11);
        country.setCurrencySymbol("aDH94qPmzTIrUOdiLPG3MVc0AP04YZAY");
        country.setCurrencyCode("3DQ");
        country.setCapital("TvvZCrn26XUyQQOvfbirEWkDFhN7xayn");
        country.setCountryName("FFPO4cTbrOvYMjwIWzg6wFsyXYR8rMIBK4eyVqcsXk7m7ovGkA");
        country.setCurrencyName("3ZLP6eLpt6KHM0APEaB117e3kKlJ84SBZLEdza9kL8Afp1YnU5");
        country.setCountryFlag("X2EfazsipDv50SDGkqLfDDseg8RauzyrwkUeW1u4O9kiQTSvlc");
        country.setCountryCode2("pr2");
        country.setCountryCode1("BAc");
        country.setIsoNumeric(585);
        country.setCapitalLatitude(2);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("AItkqcKlNlcRLVwWd7dpzcTiOhi1U1ZetzAkvV9czM6lFIRmXT");
        state.setStateName("7XyfAt4IpRjZ2WWiwh771Uwad1xV5C0Ate701O4CcqhTPcAAgO");
        state.setStateCodeChar2("K5atTNI03dyCRHeHxxs7P1e4iIKk8bdE");
        state.setStateCodeChar3("YC4Z5B1jEZ3y7vE6OnkgJCyCQnansZig");
        state.setStateCode(1);
        state.setStateCapitalLongitude(1);
        state.setStateCapital("xic0rDRlkQslR8SqAjUjhs2Idq5drG0Pxorq8QdqQiPG6eNdmS");
        state.setStateFlag("xgmrZl0lBFhGL462p13IZoDjWjh8T98xn3HNsFJEjr89bzZqiW");
        state.setStateCapitalLatitude(4);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("p2P0lGBkGSj6V9IAgI5ihyCPkjML5R3kxRX4rAEhz1Gv2ssMA1");
        addresstype.setAddressTypeIcon("b3PByskmkEak6Gkt6A4YFdniDqV5EE2numtAZa5vOB27lsq34H");
        addresstype.setAddressTypeDesc("i7OlUydGkFrkOnpzrZVTVW7L7dzLr25d9RJyCba9KyJ4ZKam7T");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityFlag("0P1Xd79KlTuAdVaHrwq7h8OgoB64wzPkA7CIzdnSwUF352DWgX");
        city.setCityCodeChar2("bGjfo6ffRojs40GFHEhKtHi1mXE6T3FU");
        city.setCityLongitude(2);
        city.setCityName("51h0xzzqDzSJVaZgtOiPT9WxyiYPVq4YTRRabqfzc0rcYnz4kj");
        city.setCityDescription("LcWNq4lGZU0hd469jtx5jWyHZvhRxtn6DhNfxsyMl4zjY6DGzB");
        city.setCityFlag("k7eJSjN5LNbc72IBJu5UQO6T4MdIwidD8tlp1b10I7OaTkLbAn");
        city.setCityCodeChar2("OFxJc5fZRLmckWLfOvGdcEocAxZ7OLrg");
        city.setCityLongitude(10);
        city.setCityName("Hyyfa8WGmEQNSV7T4mSrr2galW7qVj5hDJMN0D4wdlQeZHwRoA");
        city.setCityDescription("hsggCSoIMW2ryqQOhFCPefpUm5usr9FrsARC6g5He2fgeHj5gw");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(3);
        city.setCityLatitude(8);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("eruoDJH9gbptdtW9nj51SSNG86r90NwyiqTk4StuWIgFAmiS5o");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("ncxjR6lUlcxCdeIIhRY967Dt9YYb84sY1oIF2L4U3zhYhipkgq");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("jtjzBgAK0IFWUET7O2sdKbI25gMDaRJ14j3Ejh2VlfoWPV2600");
        address.setLatitude("uMiSJUjRg12BDHoXvKXMsxjtakEyo13aWeICbyQs4abS75ZGrj");
        address.setAddress1("1749ExvYTLQXcKE7GfzolBaS37aNczrJmeKwtSFr3HaSTRYSpN");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("KyfDRPNsyoQ");
        address.setZipcode("nQjFN4");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("I9eHtTb7h3aDf6vcQLcsylA7n29YMVkNsx6GPDNs2XbNHxzVgG");
        communicationgroup.setCommGroupDescription("lVXQIKZFTN0LzLeSyZIndA1dgca4NlJ6L8NCfP3vHYkrduPIqh");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("k8pnA2XtsXjt4XqGuA9IgAiNQhHuRp6ElM4QakHN0aDZpgbRzX");
        communicationtype.setCommTypeName("CWUv96iVHjHop2HfkZ4K50Qf2RSnIjaK4Uy9j0ClnvrPkpVA4Y");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("LmUymLlv2VZiEt2FAX6qJphKan0eRJLCZRRHMA2b0lvJeTx58s");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("YmXBHippLk");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey());
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setEmailId("DpSvOjEwSRO31HrjRDruNWIkgVzdVeYI71KjxYT2fp0pISo96E");
            corecontacts.setNativeMiddleName("74StBl6cATVHXeaeOJKyYWRNucN7q2sCj1OCBvtJgz1u5ahmVA");
            corecontacts.setAge(115);
            corecontacts.setNativeTitle("HbHuE3GcQk0AgNRXe5afj58eHN1qA5OuZXhjU4l0nqss3TCzcE");
            corecontacts.setVersionId(1);
            corecontacts.setFirstName("E1iGtAarU4s3IRyCKW95ZGFTzl5VKgrnpaBfKzvCg6w4qLGWMr");
            corecontacts.setPhoneNumber("Hiknbr9Mrv8GJfJZbzWN");
            corecontacts.setMiddleName("M2WX2zZQ8WzVNKRwtyLGlBpm0W4LIJEKblXxPk9ZHflRHIoLBO");
            corecontacts.setLastName("VuFsc6Xi2tnvih1mDDvHryFfM8ONbdAK9BVLMBIodJztLOkxya");
            corecontacts.setNativeFirstName("sTz9Me6jflNPvZVfDRGLUoZYBtLceqmyzjeqV62T2vXxwxZP5O");
            corecontacts.setNativeLastName("9MjbkdmGXUsmvor60T9zcrSQtjpexYb9qlTTa7xmfRhzVCsJKX");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1464944123209l));
            corecontacts.setDateofbirth(new java.sql.Timestamp(1464944123244l));
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "2o79Ce5njkLajNyE76oflr6wnaYVdURSusdq8oc7CrhGLkARpgUFaa9HU5GVRNmYQ7c34kYkeEX64bDa6RdQg4VL2eDuUR7NqYDC9PpocJSf4N9L5OztFeZwS7g5Upi1eKwSBMeFMIodA7vCcCDXTLiQV6ceWY0zNqaZ90zrG22Ivt7OnVjhEFVSDsJxP18A64mHWWHXajen7zONaVRUlCnuU48bSeWuFiGXy4O1GMzKGSaaugNxnHtbRWHjbcmZy"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "Wwik9r3eRvQHDwqcuHsudYkfnUL2FNBvT82IZ0hZo1bFPrK4spUAFxhGevVAmMJf5y1tPcFJdo9eTkkQOB04oeckQjV6QcFJowfEiWHqeGzDPKLjjjsneIclNwyETTMrHUV7CVHxMllVkCztJbvgsfZsjDhml0snasQGOYWkQB4GjlIqW78zNGLowaq3EzTOn20u7qItK22AGMg51zS4Bx2rNDVskLiLHfbgOUVM1GoYwsIN4aCaXfM1OFdg706n0"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "ENMRvkvq1RNqa8kTpVKZ033zQgdHZr18XxpK0X0WhnLeFzYEMAyScWDV5LhoTaaMtImVv5NFMlLFedoD0pW7mZ10otZOXZgOsKrLPPC3HWbiMW0HtRXqdAmyHqQzQlOSmHUEAfzFn9bzt1XVTt8viP1M0HiRNcuog8saf1qxW5dZ0GGpXDzW4Uc0AB08Zdo9G72weoTrbErHgAIp3sfNSw3cPf9B5TEJnNuQI2j7OrFqsAirgOJwfz7tgCBMeIvkT"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "POYSJwLFm1MAZHRcGVKRbxJ4hQdL2W7irXKxzOkllp7L0KynKeNaST2PX1ZdIdqYE"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "gj30wX4BdpJjuURECzz4vZOWrTQKprJdeaYWpAXV2DfBA52JhBPxCGGl0xp7m5DVxYFfPHYZYP2C06khjeX64vXRQUYsEEMdBldq42lGMNHan72vC6bF141iGZ2kyKa0GOjDBNS5j4hHWlkREoEYMLW5LZZo0Ycje10gMh9RFzTr46jtkF3CmakKFHcy7uC41tk2TRYFQ5qjT6VMzgTOvl5blJPdCwjiMpJz7vkoLL8f3Rk4l54srFZKWz8DpSPWf"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "J0knGRgXbIryjbGQlENIPZ9a0Y2KR30HvOfjhusvtsXYbuenZQLfOiZjzaQNblRR9wq7PbB9K7teYUfdhzcBpsqoPv37WwBvTUX0QYWhPTiIkse9qsISNmP0boYg93qTtBB4kioYbNMN7WpzPUcOx8fsDiL2wyvcbdeSNbfnjPJvtBpTnXIMQGHWGEefU7xBA85moGUKVe58EyN2cPQxPBkOT0tRbuE03tsCJac1ceo1FVUzE3yjgLeHWe3y7JAm6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "yBXmVgiPC526YheEI4e5X00atkXuMve4zVgKqDPmxc8i8re1ZlcLpFBTXxWg16Chz8xFGL4kyzqJoxXW0PUyTmg8lP9ACG8ktSM5TZqIyAehgVlm8YX3bSMUH2pvVpDukzOrb68sKTaLE4o1xstQR4AHkoyPjrANNPhDypC91uEAABgQO3khxoj92WE6rcfU2MYH6vgOUBRH7Mn69vbCrbREiE7OjxB4dODsqITBwt339orJLfCTTAkMXSRhMd9Wh"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 185));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "EIgvXKdpgrfDxQS3DJJmT1B44giuqtviUMnfmO72KugLhESgbOv4gdmF6SF6uprmmh7rjtNpoxt4eQ3lbDjA4oFEwoXaYQ4O4oJAA32daxdG2Y6xJ2PAFTYtfbKfuV1AWaJUHHniIBH9I9IjuzTNmni2xo8hnO9BbtnoN6oXK9qBiQRMm21HEw4wUoBGq694hzm4gmi9OB5inLONBv6ZbWkcXgvq1YwmoIQULi0z0cpYG2YWhuKsI4LdzI55h0W3y"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "1x4fE454zIP8Mx6AP6sTD"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
