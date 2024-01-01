package com.loop.test.day9_tasks;

public class RegistationEnums {

    static long[] creditCardNumbersForTestingSoftware = {3782_82246_310005l, 3714_49635_398431l,  378734493671000l,
             6011111111111117l, 6011000990139424l, 5555555555554444l, 5105105105105100l, 4222222222222l};

    enum EntityType{
        LLC("LLC"), CORPORATION("Corporation");
        private String entityType;
        private EntityType(String entityType){this.entityType = entityType;}

        public String getEntityType() {return entityType;}
    }
    enum FormationState {
        DELAWARE("Delaware"), MARYLAND("Maryland"), VIRGINIA("Virginia"), WASHINGTON_DC("Washington D.C.");
        private String stateName;
        private  FormationState(String stateName) {this.stateName = stateName;}

        public String getStateName() {return stateName;}
    }

    enum AgentType{
        INDIVIDUAL("Individual"), COMPANY("Company");
        private String agentType;
        private AgentType(String agentType){this.agentType = agentType;}

        public String getAgentType() {return agentType;}
    }

    enum IndustryType {
        ACCOUNTING("Accounting"),
        AGENTS("Agents"),
        AGRICULTURE("Agriculture"),
        ANTIQUES_AND_COLLECTABLES("Antiques and Collectables"),
        ARTS_AND_CRAFTS("Arts and Crafts"),
        ASSET_MANAGEMENT("Asset Management"),
        AUTOMATIVE("Automative"),
        BEVERAGES("Beverages"),
        BROKERS("Brokers"),
        BUSINESS_SERVICES("Business Services"),
        CHILD_CARE("Child Care"),
        CLEANING_SERVICES("Cleaning Services"),
        DISTRIBUTOR("Distributor"),
        ECOMMERCE("Ecommerce"),
        EDUCATION_AND_TRAINING("Education & Training"),
        FASHION("Fashion"),
        FOOD_SERVICES("Food Services"),
        GARDENING_AND_LANDSCAPING("Gardening and Landscaping"),
        HEALTH_AND_BEAUTY("Health and Beauty"),
        INFORMATION_TECHNOLOGY("Information Technology"),
        LEGAL_SERVICES("Legal Services"),
        MAINTENANCE_AND_REPAIR("Maintenance and Repair"),
        MANAGEMENT_SERVICES("Management Services"),
        MANUFACTURING("Manufacturing"),
        MARKETING_SERVICES("Marketing Services"),
        MEDIA("Media"),
        MEDICAL_PRACTITIONERS("Medical Practitioners"),
        MUSIC("Music"),
        NIGHTLIFE("Nightlife"),
        PERSONAL_SERVICES("Personal Services"),
        PET_SERVICES("Pet Services"),
        PHOTOGRAPHY("Photography"),
        PROFESSIONAL_SERVICES("Professional Services"),
        PUBLISHING("Publishing"),
        RECRUITING_AND_STAFFING("Recruiting and Staffing"),
        RENTAL_AND_LEASING("Rental and Leasing"),
        RESEARCH_SERVICES("Research Services"),
        RETAIL("Retail"),
        SHIPPING_AND_DELIVERY("Shipping and Delivery"),
        SUSTAINABILITY("Sustainability"),
        TOYS_AND_HOPPIES("Toys and Hoppies"),
        TRANSPORTATION("Transportation"),
        TRAVEL_AND_TOURISM("Travel and Tourism"),
        VALUE_ADDED_RESELLER("Value Added Reseller"),
        WAREHOUSING_AND_STORAGE("Warehousing and Storage"),
        WHOLESALE("Wholesale"),
        OTHER("Other");
        private String optionName;
        private  IndustryType(String optionName) {this.optionName = optionName;}
        public String getOptioName() {return optionName;}
    }

    enum Jurisdiction {
        KENT("Kent County"),
        NEW_CASTLE("New Castle County"),
        SUSSEX("Sussex County");
        private String county;
        private Jurisdiction(String county){this.county = county;}
        public String getCounty(){return county;}
    }

    enum Jurisdiction2 {
        ACCOMACK("Accomack"),
        ALBEMARLE("Albemarle"),
        ALEXANDRIA("Alexandria"),
        ALLEGHANY("Alleghany"),
        AMELIA("Amelia"),
        AMHERST("Amherst"),
        APPOMATTOX("Appomattox"),
        ARLINGTON("Arlington"),
        AUGUSTA("Augusta"),
        BATH("Bath"),
        BEDFORD("Bedford"),
        BEDFORD_CITY("Bedford (City),"),
        BEDFORD_COUNTY("Bedford (County),"),
        BLAND("Bland"),
        BOTETOURT("Botetourt"),
        BRISTOL("Bristol"),
        BRUNSWICK("Brunswick"),
        BUCHANAN("Buchanan"),
        BUCKINGHAM("Buckingham"),
        BUENA_VISTA("Buena Vista"),
        CAMPBELL("Campbell"),
        CAROLINE("Caroline"),
        CARROLL("Carroll"),
        CHARLES_CITY("Charles City"),
        CHARLES_COUNTY("Charles County"),
        CHARLOTTE("Charlotte"),
        CHARLOTTESVILLE("Charlottesville"),
        CHESAPEAKE("Chesapeake"),
        CHESTERFIELD("Chesterfield"),
        CLARKE("Clarke"),
        COLONIAL_HEIGHTS("Colonial Heights"),
        COVINGTON("Covington"),
        CRAIG("Craig"),
        CULPEPER("Culpeper"),
        CUMBERLAND("Cumberland"),
        DANVILLE("Danville"),
        DICKENSON("Dickenson"),
        DINWIDDIE("Dinwiddie"),
        EMPORIA("Emporia"),
        ESSEX("Essex"),
        FAIRFAX("Fairfax"),
        FAIRFAX_CITY("Fairfax (City),"),
        FAIRFAX_COUNTY("Fairfax (County),"),
        FALLS_CHURCH("Falls Church"),
        FAUQUIE("Fauquie"),
        FLOYD("Floyd"),
        FLUVANNA("Fluvanna"),
        FRANKLIN("Franklin"),
        FRANKLIN_CITY("Franklin (City),"),
        FRANKLIN_COUNTY("Franklin (County),"),
        FREDERICK("Frederick"),
        FREDERICKSBURG("Fredericksburg"),
        GALAX("Galax"),
        GILES("Giles"),
        GLOUCESTER("Gloucester"),
        GOOCHLAND("Goochland"),
        GRAYSON("Grayson"),
        GREENE("Greene"),
        GREENSVILLE("Greensville"),
        HALIFAX("Halifax"),
        HAMPTON("Hampton"),
        HANOVER("Hanover"),
        HARRISONBURG("Harrisonburg"),
        HENRICO("Henrico"),
        HENRY("Henry"),
        HIGHLAND("Highland"),
        HOPEWELL("Hopewell"),
        ISLE_OF_WIGHT("Isle of Wight"),
        JAMES_CITY("James City"),
        JAMES_COUNTY("James County"),
        KING_AND_QUEEN("King and Queen"),
        KING_GEORGE("King George"),
        KING_WILLIAM("King William"),
        LANCASTER("Lancaster"),
        LEE("Lee"),
        LEXINGTON("Lexington"),
        LOUDOUN("Loudoun"),
        LOUISA("Louisa"),
        LUNENBURG("Lunenburg"),
        LYNCHBURG("Lynchburg"),
        MADISON("Madison"),
        MANASSAS("Manassas"),
        MANASSAS_PARK("Manassas Park"),
        MARTINSVILLE("Martinsville"),
        MATHEWS("Mathews"),
        MECKLENBURG("Mecklenburg"),
        MIDDLESEX("Middlesex"),
        MONTGOMERY("Montgomery"),
        NELSON("Nelson"),
        NEW_KENT("New Kent"),
        NEWPORT_NEWS("Newport News"),
        NORFOLK("Norfolk"),
        NORTHAMPTON("Northampton"),
        NORTHUMBERLAND("Northumberland"),
        NORTON("Norton"),
        NOTTOWAY("Nottoway"),
        ORANGE("Orange"),
        PAGE("Page"),
        PATRICK("Patrick"),
        PETERSBURG("Petersburg"),
        PITTSYLVANIA("Pittsylvania"),
        PORTSMOUTH("Portsmouth"),
        POWHATAN("Powhatan"),
        PRINCE_EDWARD("Prince Edward"),
        PRINCE_GEORGE("Prince George"),
        PRINCE_WILLIAM("Prince William"),
        PULASKI("Pulaski"),
        RADFORD("Radford"),
        RAPPAHANNOCK("Rappahannock"),
        RICHMOND("Richmond"),
        RICHMOND_CITY("Richmond (City),"),
        RICHMOND_COUNTY("Richmond (County),"),
        ROANOKE("Roanoke"),
        ROANOKE_CITY("Roanoke (City),"),
        ROANOKE_COUNTY("Roanoke (County),"),
        ROCKBRIDGE("Rockbridge"),
        ROCKINGHAM("Rockingham"),
        RUSELL("Rusell"),
        SALEM("Salem"),
        SCOTT("Scott"),
        SHENANDOAH("Shenandoah"),
        SMYTH("Smyth"),
        SOUTHAMPTON("Southampton"),
        SPOTSYLVANIA("Spotsylvania"),
        STAFFORD("Stafford"),
        STAUNTON("Staunton"),
        SUFFOLK("Suffolk"),
        SURRY("Surry"),
        SUSSEX("Sussex"),
        TAZEWELL("Tazewell"),
        VIRGINIA_BEACH("Virginia Beach"),
        WARREN("Warren"),
        WASHINGTON("Washington"),
        WAYNESBORO("Waynesboro"),
        WESTMORELAND("Westmoreland"),
        WILLIAMSBURG("Williamsburg"),
        WINCHESTER("Winchester"),
        WISE("Wise"),
        WYTHE("Wythe"),
        YORK("York");
        private String county;
        private  Jurisdiction2(String county) {this.county = county;}
        public String getCounty() {return county;}
    }

    enum Jurisdiction3 {
    ALLEGANY_COUNTY("Allegany County"),
    ANNE_ARUNDEL_COUNTY("Anne Arundel County"),
    BALTIMORE_CITY("Baltimore City"),
    BALTIMORE_COUNTY("Baltimore County"),
    CALVERT_COUNTY("Calvert County"),
    CAROLINE_COUNTY("Caroline County"),
    CARROLL_COUNTY("Carroll County"),
    CECIL_COUNTY("Cecil County"),
    CHARLES_COUNTY("Charles County"),
    DORCHESTER_COUNTY("Dorchester County"),
    FREDERICK_COUNTY("Frederick County"),
    GARRETT_COUNTY("Garrett County"),
    HARFORD_COUNTY("Harford County"),
    HOWARD_COUNTY("Howard County"),
    KENT_COUNTY("Kent County"),
    MONTGOMERY_COUNTY("Montgomery County"),
    PRINCE_GEORGES_COUNTY("Prince George's County"),
    QUEEN_ANNES_COUNTY("Queen Anne's County"),
    SOMERSET_COUNTY("Somerset County"),
    ST_MARYS_COUNTY("St. Mary's County"),
    TALBOT_COUNTY("Talbot County"),
    WASHINGTON_COUNTY("Washington County"),
    WICOMICO_COUNTY("Wicomico County"),
    WORCESTER_COUNTY("Worcester County");
    private String county;
    private  Jurisdiction3(String county) {this.county = county;}
    public String getCounty() {return county;}
}


}
