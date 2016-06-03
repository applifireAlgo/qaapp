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
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setEmailId("h0q7I3A5J7SUAa8iQBaCUx1qiDx0U7PfwMkeJPkQGueZKpGxnm");
        Gender gender = new Gender();
        gender.setGender("KQ7TTZOT9yNhFSpkrXSvBWP87ZQBeDhTUWlua9qnneBqZ1luj3");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguageIcon("i7B3Xlmq3gFWqfjPctDZrF4nka7eOtq6ArXwXqKZJrga7QSHuf");
        language.setAlpha4("0yhA");
        language.setLanguage("PvSbxPCamIDqYKxJl89OKhdHPqM4Jehyg2bEBCGjCRm18RUky1");
        language.setAlpha2("Cb");
        language.setLanguageDescription("xzRZrsAtzAmcwGI65kTMJPhTCsMFLsIUw9PG1bkfUvH4u2UhGc");
        language.setLanguageType("OHkUkvCMcxZrPXFwbPQfnRldsfttep96");
        language.setAlpha4parentid(7);
        language.setAlpha3("ngH");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("fLqQLkMQ4gjKKT4hrYTz1C8icDTrK6tJWr8KsvN9deD0locmaS");
        timezone.setCountry("KhPl3etgNuNIZqUFZ6hsfAAYG25b6AE82V15LOD9s1zSkrSDKS");
        timezone.setTimeZoneLabel("qZZ1q8A1GpRGinEBucO5wKy2TejUpVHh9gZe9wJrRgYn8ULQUf");
        timezone.setCities("noyfixSETrE4uQ9YyZFdl0LITU2923DgeP9l4kmy7UgsyxKwYO");
        timezone.setUtcdifference(10);
        Title title = new Title();
        title.setTitles("CubhTKQQRkG1CeqK217VMNGm6XQVtNgYoL35Dtb8nmW2GAYHqg");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        corecontacts.setEmailId("IigoNzcC3Gs77WugNmfGnqJsUv8uoHCnGex051mcZfPD02h5g2");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeMiddleName("QctakBJsP62SkLlK74vLiBE72a0i2NT4XkuHgsjikxk2VGhn8o");
        corecontacts.setAge(84);
        corecontacts.setNativeTitle("s72DoM3oTfreGEJjUA48nIwRdXdSvvm6efrP52LMGIQbokGSRr");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("SglIOzLAUPKTsgazStuj5BR4DNlwp2tHtRXfZVcz6WiTTlTHNu");
        corecontacts.setPhoneNumber("1KGCvKh4LiQce5oER1Zu");
        corecontacts.setMiddleName("9OpSqx8TSXQVpxydosfSLUVkRizECPhPYSjR37JGKnZO3UyWG5");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setLastName("en32xUN6TePUDUp3bRsxKKDCrtp9DqmU8BvTGlFqzU2KS2En4k");
        corecontacts.setNativeFirstName("WkZVspsmtrkBHp8rPwET9tYEbmlER2klcXMaBFrsvNc2exOfyQ");
        corecontacts.setNativeLastName("6zgZgSbwwFA7kTRkKJu1ArjwSDcN4Um3PEBPNqM1IxlZ44WhX6");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1464944140900l));
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1464944140986l));
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        State state = new State();
        Country country = new Country();
        country.setCapitalLongitude(4);
        country.setCurrencySymbol("oVsOVeFsRkE6bOHD8YUFQorkRXcrQyk4");
        country.setCurrencyCode("nge");
        country.setCapital("SEZOddh5dMlJRNkmZQgqgo95wf6EAkVE");
        country.setCountryName("Vz1hzkDwcbzcsFX7fe7fUfW3VLjdqZT6cP3UTC4ogWec5p8cIW");
        country.setCurrencyName("WHPZmEWdHqUPKfbbS7sVDT3sDgDWD7eJlwQ78MNOZYrYwkAeFU");
        country.setCountryFlag("RtboZ7xIAu4qz96885nHSo6xgDu8BRO2U4HZI8MiL0UsJegekY");
        country.setCountryCode2("Vyv");
        country.setCountryCode1("UHy");
        country.setIsoNumeric(974);
        country.setCapitalLatitude(6);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("yZ6vrw5HYzemU0mR4ow5B0a45nck2MGmOULFaWo5zuZALwsOWo");
        state.setStateName("YThZIY4G2hN4F15af8ugELuL2N3etkB5wBgAwuAxQFgGr8eoO3");
        state.setStateCodeChar2("5N0ahI1DCJ0byFLzmrr86T9gXQfdnalD");
        state.setStateCodeChar3("xyxSfV4c8hRAi5PDtFiCprxGgPjHcKdb");
        state.setStateCode(1);
        state.setStateCapitalLongitude(4);
        state.setStateCapital("7FdifvVjJmYqau6GiHD6WFyoPXmCIcGnZ983gC2UZCwndobgFq");
        state.setStateFlag("RL3oZ88nn4gakTkB4m3GVnjK4SGJ7fZXRmD0b6Ryd7j42znqtG");
        state.setStateCapitalLatitude(5);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("MXq90whFgGw037ijUmc7oOn2weGUYs81vfPa3AMJvUy84Nz5KH");
        addresstype.setAddressTypeIcon("iM2Aq1e3DuBYoMS92lNOlAEnwL8jjeqao3oJ2O1E8FfsxxWNcp");
        addresstype.setAddressTypeDesc("2QQpXcoPXdTpCS8ennAPKuZFcajCrDjZI7cXeaOqY9nvWc9Iea");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityFlag("uFDBuiw45u36rxpuDlSROiZLTFTUOBDRna4rCRWDl9ypem8Xjg");
        city.setCityCodeChar2("RZqX9EcRVxCUfJ8Z0ArzSDYP81Nvqt6N");
        city.setCityLongitude(9);
        city.setCityName("PwgjJs8JciBpE2xkX2FmdMwn5wMWQGRAX2ANS1UhLyF1gf1Xq8");
        city.setCityDescription("NG2ImWtD6LiTJY9QbofxDqX7hPFGQH5HCYh46ihOJjbVNirPHd");
        city.setCityFlag("REwAkNlEYK7GuocywfLsFffyg2LeimTRA76lKZah26sKsVAzot");
        city.setCityCodeChar2("pSjmQcP1TvyHRfiFC30d0O2JydI7e6at");
        city.setCityLongitude(10);
        city.setCityName("fLmWEkHTa446820yTckbtaKHoziLAANzidqyJheQGXzvMK5bLB");
        city.setCityDescription("I7xbAIvO21Oa5wDljb1xWThNtbfuVQNA5LcHvRq59H8xQrPO3B");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(2);
        city.setCityLatitude(7);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("571hWiOg48i6pGjeg1O5BLxGrEyNQdhbJfiGQysQGTyDzU04fw");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("VZyi2bCaBcjdpYZDZASlYYqYFgOkLjtWdQ26nV4A86JomIC8IB");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("tcSmy3Ivs85fKBlsxOdTt9TGCvOsmae8RwcUURCGxMXbjHxnue");
        address.setLatitude("3sbZXTqcd1A5bWdCdly4m73zf342WbWmLs2Wh1P6VSBvpD4CEs");
        address.setAddress1("ZXDY84v61lCNGXq42XApHusKwInQIonBuzlS6IMe1jmt2bXgB1");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("yil9wIKpDtU");
        address.setZipcode("wZ1cev");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("SGicmGrsjzNjkcF8wXohZIOsp3agCLXcWfJ2PnhCDjQAzCalsE");
        communicationgroup.setCommGroupDescription("h7cQy4plHe52BQ2xUVCkWcMGBxu6KOdHLklizlhtasNUrREkag");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("tNBhlI7B9VF1frWzwXxuSgNZIQacONOscfgYw6oUHIKrkibzhv");
        communicationtype.setCommTypeName("CLDEAVmisLJeHcHC12C4uEVdXmkr5DR70LP0W9gGStPMQtGNgO");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("n2jCrPcT0ePeyWGSBPfzlTfrTmxIb81jcPJVO2q89A3UAhg5KH");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("5wlUIm3dJq");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        User user = new User();
        user.setUserAccessCode(48283);
        user.setPasswordAlgo("Sgbwmn5WjmogLCIvJ1qMrrLfbKjPurIUgK58xIxADeoQgxumWy");
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("j1UHAEMPUJaNdSHwKDUQrsoSt0HmFB16iXuASczd7btS5xYuMW");
        useraccesslevel.setLevelHelp("sl6Fj7rmkbqAEvJmeXp4Pf4IZ4iMPOFFrr1orR29mmJM43A3j4");
        useraccesslevel.setLevelName("Q2OGIY8vbNGcVhwcfpCH0X5bYzDRuTfXfBZ1gsLpm8YUgoAV2v");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("hNuCJgf34kMvnxUTv7twotFLlfkfnCypo5HRUzzuvikeY3TVAY");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("nDwhvIDAd3Q6k38mxl3uchRibjmnRZvaXygDQquBNLNzogslXX");
        useraccessdomain.setDomainIcon("6NuDCF6VrN2UZbCayUiXHU007PWGcj4T9EHAV9pU458S72qMOT");
        useraccessdomain.setDomainHelp("ecmIuhGFuxxnDYAfoN9GpNemknabYr2OU3U8NxFBLYOyVAxWRI");
        useraccessdomain.setDomainDescription("ozZu9H4MbHVaO5IfghEJx8Z5wJAIxu0GxBmDYEoHGfDAHDB29f");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setUserAccessCode(58052);
        user.setPasswordAlgo("zKVMDiFmCd1puqaSZZZtkJo6qIpnZmGhEI721yqojsiO9ZV7X0");
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordExpiryDate(new java.sql.Timestamp(1464944142164l));
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1464944142164l));
        user.setIsLocked(1);
        user.setGenTempOneTimePassword(1);
        user.setIsDeleted(1);
        user.setSessionTimeout(1029);
        user.setMultiFactorAuthEnabled(1);
        user.setAllowMultipleLogin(1);
        user.setChangePasswordNextLogin(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionDetails("rGydCvALzs");
        question.setLevelid(3);
        question.setQuestion("O1aBc0INSvyrMflqDHJlXCCHQKpe2Q0bijfCpOpUJXZGsbCjIX");
        question.setQuestionIcon("ANrdG705cctFuWZT5Da2loSZqApCz0thQSNlW05aTpV4IKuMPi");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        passrecovery.setUser(user);
        passrecovery.setAnswer("b9pMPTs3INS5yQijvQeoDWfpwYyIj2v3holrtN1opqXlkuKByc");
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464944142615l));
        userdata.setPassword("bgpmqy0uhpt1bm053uzP8dHSKICz4uXkkAAsyP9i6yRkE9ZeES");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464944142664l));
        userdata.setPassword("MceTbIyJCQXc8l6rS4GeqTGIRFiGtIlk4xFeQgPm6edGa660EQ");
        userdata.setUser(user);
        userdata.setOneTimePassword("8qj8JirAT3vmFR4cR4LoRw7prRobG3BH");
        userdata.setLast5Passwords("Ef1rSl0i0k0a73uDth6JX43JXPXWCKn6NmneIuQ4fVARnj2rVI");
        userdata.setOneTimePasswordExpiry(4);
        user.setUserData(userdata);
        Login login = new Login();
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setServerAuthImage("tVXZMsBYG1eCgUmUiVSJ89W6ktCCb78p");
        login.setIsAuthenticated(true);
        login.setLoginId("Wx2JrQbP8hoAKbc1oSZQT6PXq0ICCnsxWfUdwJwTNkX8tuNQqH");
        login.setServerAuthText("ayezl62juUJTXS6h");
        user.setUserId(null);
        login.setUser(user);
        login.setFailedLoginAttempts(11);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthImage("cSCD9Ir6kwluenbncAj9rESNU0HPkhBp");
            login.setVersionId(1);
            login.setLoginId("eHtymz1QWogJkbKLhRkXrk0OdmGGEPZxTPb4Z5r898oXzK86eg");
            login.setServerAuthText("0Qarl5VQfMdBNB0h");
            login.setFailedLoginAttempts(10);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
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

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "oyVYw4okKpAGD72ALNAFP2TbXm7bU3MfIR8guJkUQu3ED8s30U0V043qdulCy7LfKspkQBYcnyUWK7TIou45o1SYDHenlreyTuIjCXF0uoDrhw1dzcyj0YwLORjI4P4NAcDbofVBl4UPN5Vd87mwNiSL3F4ffFDuWsmiZdZMhf8SXaJRensTVjLkygO8thHB9XTsxHIVR"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "uvRd5PBGsdwqXVXkfRWKJLP4d3QQTv0R8"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "vnpszpM3xZ4sHLkJX"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 14));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
