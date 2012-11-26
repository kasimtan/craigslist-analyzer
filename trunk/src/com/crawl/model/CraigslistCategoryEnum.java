package com.crawl.model;

import org.apache.log4j.Logger;

/**
 * Craigslist Categories.
 * @author Team Kappa.
 *
 */
public enum CraigslistCategoryEnum{
    
	MAIN_TOPIC__COMMUNITY("ccc", "Community"), 
	MAIN_TOPIC__PERSONALS("", "Personals"), 
	MAIN_TOPIC__DISCUSSION_FORUMS("forums", "Discussion Forums"), 
	MAIN_TOPIC__HOUSING("hhh", "Housing"), 
	MAIN_TOPIC__FOR_SALE("sss", "For Sale"), 
	MAIN_TOPIC__SERVICES("bbb", "Services"),
	MAIN_TOPIC__JOBS("jjj", "Jobs"),
	MAIN_TOPIC__GIGS("ggg", "Gigs"),
	MAIN_TOPIC__RESUMES("res", "Resumes"),
	
	COMMUNITY__ACTIVITIES("", "Activities"),
	COMMUNITY__ARTISTS("", "Artists"),
	COMMUNITY__CHILDCARE("", "Childcare"),
	COMMUNITY__GENERAL("", "General"),
	COMMUNITY__GROUPS("", "Groups"),
	COMMUNITY__PETS("", "Pets"),
	COMMUNITY__EVENTS("", "Events"),
	COMMUNITY__LOST_FOUND("", "Lost&Found"),
	COMMUNITY__MUSICIANS("", "Musicians"),
	COMMUNITY__LOCAL_NEWS("", "Local News"),
	COMMUNITY__POLITICS("", "Politices"),
	COMMUNITY__RIDESHARE("", "Rideshare"),
	COMMUNITY__VOLUNTEERS("", "Volunteers"),
	COMMUNITY__CLASSES("", "Classes"),
	
	PERSONALS__STRICTLY_PLATONIC("", "Strictlz Platonic"),
	PERSONALS__WOMEN_SEEK_WOMEN("", "Seek Woman"),
	PERSONALS__WOMEN_SEEKING_MEN("", "Seeking Men"),
	PERSONALS__MEN_SEEKING_WOMEN("", "Seeking Woman"),
	PERSONALS__MEN_SEEKING_MEN("", "Seeking Men"),
	PERSONALS__MISC_ROMANCE("", "Romance"),
	PERSONALS__CASUAL_ENCOUNTERS("", "Encounters"),
	PERSONALS__MISSED_CONNECTIONS("", "Connections"),
	PERSONALS__RANTS_AND_RAVES("", "Rants&Raves"),
	
	DISCUSSION_FORUMS__1099("forums/?forumID="+10, "1099"),
	DISCUSSION_FORUMS__APPLE("forums/?forumID="+10, "Apple"),
	DISCUSSION_FORUMS__ARTS("forums/?forumID="+10, "Arts"),
	DISCUSSION_FORUMS__ATHEIST("forums/?forumID="+10, "Atheist"),
	DISCUSSION_FORUMS__AUTOS("forums/?forumID="+10, "Autos"),
	DISCUSSION_FORUMS__BEAUTY("forums/?forumID="+10, "Beauty"),
	DISCUSSION_FORUMS__BIKES("forums/?forumID="+10, "Bikes"),
	DISCUSSION_FORUMS__CELEBS("forums/?forumID="+10, "Celebs"),
	DISCUSSION_FORUMS__COMP("forums/?forumID="+10, "Comp"),
	DISCUSSION_FORUMS__CRAFTS("forums/?forumID="+10, "Crafts"),
	DISCUSSION_FORUMS__DIET("forums/?forumID="+10, "Diet"),
	DISCUSSION_FORUMS__DIVORCE("forums/?forumID="+10, "Divorce"),
	DISCUSSION_FORUMS__DYING("forums/?forumID="+10, "Dying"),
	DISCUSSION_FORUMS__ECO("forums/?forumID="+10, "ECO"),
	DISCUSSION_FORUMS__EDUC("forums/?forumID="+10, "EDUC"),
	DISCUSSION_FORUMS__ETIQUET("forums/?forumID="+10, "Etiquet"),
	DISCUSSION_FORUMS__FEEDBK("forums/?forumID="+10, "Feedback"),
	DISCUSSION_FORUMS__FILM("forums/?forumID="+10, "Film"),
	DISCUSSION_FORUMS__FITNESS("forums/?forumID="+10, "Fitness"),
	DISCUSSION_FORUMS__FIXIT("forums/?forumID="+10, "Fix it"),
	DISCUSSION_FORUMS__FOOD("forums/?forumID="+10, "Food"),
	DISCUSSION_FORUMS__FRUGAL("forums/?forumID="+10, "Frugal"),
	DISCUSSION_FORUMS__GAMING("forums/?forumID="+10, "Gaming"),
	DISCUSSION_FORUMS__GARDEN("forums/?forumID="+10, "Garden"),
	DISCUSSION_FORUMS__GIFTS("forums/?forumID="+10, "Gifts"),
	DISCUSSION_FORUMS__HAIKU("forums/?forumID="+10, "Haiku"),
	DISCUSSION_FORUMS__HEALTH("forums/?forumID="+10, "Health"),
	DISCUSSION_FORUMS__HELP("forums/?forumID="+10, "Help"),
	DISCUSSION_FORUMS__HISTORY("forums/?forumID="+10, "History"),
	DISCUSSION_FORUMS__HOUSING("forums/?forumID="+10, "Housing"),
	DISCUSSION_FORUMS__JOBS("forums/?forumID="+10, "Jobs"),
	DISCUSSION_FORUMS__JOKES("forums/?forumID="+10, "Jokes"),
	DISCUSSION_FORUMS__KINK("forums/?forumID="+10, "Kink"),
	DISCUSSION_FORUMS__LTR("forums/?forumID="+10, "Ltr"),
	DISCUSSION_FORUMS__LEGAL("forums/?forumID="+10, "Legal"),
	DISCUSSION_FORUMS__LINUX("forums/?forumID="+10, "Linux"),
	DISCUSSION_FORUMS__LOCPOL("forums/?forumID="+10, "Locpol"),
	DISCUSSION_FORUMS__M4M("forums/?forumID="+10, "M4M"),
	DISCUSSION_FORUMS__MONEY("forums/?forumID="+10, "Money"),
	DISCUSSION_FORUMS__MOTOCY("forums/?forumID="+10, "Motocy"),
	DISCUSSION_FORUMS__MUSIC("forums/?forumID="+10, "Music"),
	DISCUSSION_FORUMS__NPO("forums/?forumID="+10, "NPO"),
	DISCUSSION_FORUMS__OPEN("forums/?forumID="+10, "Open"),
	DISCUSSION_FORUMS__OUTDOOR("forums/?forumID="+10, "Outdoor"),
	DISCUSSION_FORUMS__OVER50("forums/?forumID="+10, "Over50"),
	DISCUSSION_FORUMS__POC("forums/?forumID="+10, "Poc"),
	DISCUSSION_FORUMS__PARENT("forums/?forumID="+10, "Parent"),
	DISCUSSION_FORUMS__PEFO("forums/?forumID="+10, "Pefo"),
	DISCUSSION_FORUMS__PETS("forums/?forumID="+10, "Pets"),
	DISCUSSION_FORUMS__PHILOS("forums/?forumID="+10, "Philos"),
	DISCUSSION_FORUMS__POLITIC("forums/?forumID="+10, "Politic"),
	DISCUSSION_FORUMS__PSYCH("forums/?forumID="+10, "Psych"),
	DISCUSSION_FORUMS__QUEER("forums/?forumID="+10, "Queer"),
	DISCUSSION_FORUMS__RECOVER("forums/?forumID="+10, "Recover"),
	DISCUSSION_FORUMS__RELIGION("forums/?forumID="+10, "Religion"),
	DISCUSSION_FORUMS__ROFO("forums/?forumID="+10, "Rofo"),
	DISCUSSION_FORUMS__SCIENCE("forums/?forumID="+10, "Science"),
	DISCUSSION_FORUMS__SHOP("forums/?forumID="+10, "Shop"),
	DISCUSSION_FORUMS__SPIRIT("forums/?forumID="+10, "Spirit"),
	DISCUSSION_FORUMS__SPORTS("forums/?forumID="+10, "Sports"),
	DISCUSSION_FORUMS__TV("forums/?forumID="+10, "TV"),
	DISCUSSION_FORUMS__TAX("forums/?forumID="+10, "Tax"),
	DISCUSSION_FORUMS__TESTING("forums/?forumID="+10, "Testing"),
	DISCUSSION_FORUMS__TRANSG("forums/?forumID="+10, "Transg"),
	DISCUSSION_FORUMS__TRAVEL("forums/?forumID="+10, "Travel"),
	DISCUSSION_FORUMS__VEGAN("forums/?forumID="+10, "Vegan"),
	DISCUSSION_FORUMS__W4W("forums/?forumID="+10, "W4W"),
	DISCUSSION_FORUMS__WED("forums/?forumID="+10, "Wed"),
	DISCUSSION_FORUMS__WINE("forums/?forumID="+10, "Wine"),
	DISCUSSION_FORUMS__WOMEN("forums/?forumID="+10, "Woman"),
	DISCUSSION_FORUMS__WORDS("forums/?forumID="+10, "Words"),
	DISCUSSION_FORUMS__WRITERS("forums/?forumID="+10, "Writers"),

	HOUSING__APTS_HOUSING("", "Apartments&Housing"),
	HOUSING__ROOMS_SHARED("", "Rooms for Share"),
	HOUSING__SUBLETS_TEMPORARY("", "Subtlets Temporary"),
	HOUSING__HOUSINGWANTED("", "Housing Wanted"),
	HOUSING__HOUSINGSWAP("", "Housing Swap"),
	HOUSING__VACATIONRENTALS("", "Vacation Rentals"),
	HOUSING__PARKING_STORAGE("", "Parking Storage"),
	HOUSING__OFFICE_COMMERCIAL("", "Office Commercial"),
	HOUSING__REALESTATEFORSALE("", "Reaestate for Sale"),

	FOR_SALE__APPLIANCES("ppa", "Appliances"), 
	FOR_SALE__ANTIQUES("ata", "Antiques"), 
	FOR_SALE__BARTER("bar", "Barter"), 
	FOR_SALE__BIKES("bia", "Bikes"), 
	FOR_SALE__BOATS("boo", "Boats"), 
	FOR_SALE__BOOKS("bka", "Books"), 
	FOR_SALE__BUSINESS("bfa", "Business"), 
	FOR_SALE__COMPUTER("sya", "Computer"), 
	FOR_SALE__FREE("zip", "Free"), 
	FOR_SALE__FURNITURE("fua", "Furniture"), 
	FOR_SALE__GENERAL("foa", "General"), 
	FOR_SALE__JEWELRY("jwa", "Jewlerz"), 
	FOR_SALE__MATERIALS("maa", "Materials"), 
	FOR_SALE__RVS("rva", "RVA"), 
	FOR_SALE__SPORTING("sga", "Sporting"), 
	FOR_SALE__TICKETS("tia", "Tickets"), 
	FOR_SALE__TOOLS("tla", "Tools"), 
	FOR_SALE__WANTED("wan", "Wanted"), 
	FOR_SALE__ARTS_CRAFTS("ara", "Arts&Crafts"), 
	FOR_SALE__AUTO_PARTS("pta", "Auto Parts"), 
	FOR_SALE__BABY_KIDS("baa", "Kids"), 
	FOR_SALE__BEAUTY_HLTH("haa", "Beauty&Health"), 
	FOR_SALE__CARS_TRUCKS("i/autos", "Trucks"), 
	FOR_SALE__CDS_DVD_VHS("ema", "DVD&VHS"), 
	FOR_SALE__CELL_PHONES("moa", "Cell Phones"), 
	FOR_SALE__CLOTHES_ACC("cla", "Clothes ACC"), 
	FOR_SALE__COLLECTIBLES("cba", "Collectibles"), 
	FOR_SALE__ELECTRONICS("ela", "Electronics"), 
	FOR_SALE__FARM_GARDEN("gra", "Fram&Garden"), 
	FOR_SALE__GARAGE_SALE("gms", "Garage Sale"), 
	FOR_SALE__HOUSEHOLD("hsa", "Household"), 
	FOR_SALE__MOTORCYCLES("mca", "Motorcycles"), 
	FOR_SALE__MUSIC_INSTR("msa", "Music Instruments"), 
	FOR_SALE__PHOTO_VIDEO("pha", "Photo Videos"), 
	FOR_SALE__TOYS_GAMES("taa", "Toys&Games"), 
	FOR_SALE__VIDEO_GAMING("vga", "Video Gaming"),
	
	SERVICES__BEAUTY("", "Beauty"),
	SERVICES__CREATIVE("", "Creative"),
	SERVICES__COMPUTER("", "Computer"),
	SERVICES__CYCLE("", "Cycle"),
	SERVICES__EVENT("", "Event"),
	SERVICES__FINANCIAL("", "Financial"),
	SERVICES__LEGAL("", "Legal"),
	SERVICES__LESSONS("", "Lessons"),
	SERVICES__MARINE("", "Marine"),
	SERVICES__PET("", "Pet"),
	SERVICES__AUTOMOTIVE("", "Automotive"),
	SERVICES__FARM_GARDEN("", "Farm&Garden"),
	SERVICES__HOUSEHOLD("", "Household"),
	SERVICES__LABOR_MOVE("", "Labor Move"),
	SERVICES__SKILL_DTRADE("", "Skill Dtrade"),
	SERVICES__REALESTATE("", "Realstate"),
	SERVICES__SMBIZADS("", "Smbizads"),
	SERVICES__THERAPEUTIC("", "Therapeutic"),
	SERVICES__TRAVEL_VAC("", "Travel Vac"),
	SERVICES__WRITE_ED_TR8("", "Write Ed Tr8"),
	
	JOBS__ACCOUNTING_FINANCE("", "Accounting Finance"),
	JOBS__ADMIN_OFFICE("", "Admin Office"),
	JOBS__ARCH_ENGINEERING("", "Arch Engineering"),
	JOBS__ART_MEDIA_DESIGN("", "Art Media Design"),
	JOBS__BIOTECH_SCIENCE("", "Biotech Science"),
	JOBS__BUSINESS_MGMT("", "Business Management"),
	JOBS__CUSTOMERSERVICE("", "Customer Service"),
	JOBS__EDUCATION("", "Education"),
	JOBS__FOOD_BEV_HOSP("", "Food Bev Hosp"),
	JOBS__GENERALLABOR("", "General Labor"),
	JOBS__GOVERNMENT("", "Government"),
	JOBS__HUMANRESOURCES("", "Human Resources"),
	JOBS__INTERNETENGINEERS("", "Internet Engineers"),
	JOBS__LEGAL_PARALEGAL("", "Legal Paralegal"),
	JOBS__MANUFACTURING("", "Manufacturing"),
	JOBS__MARKETING_PR_AD("", "Marketing Pr Ad"),
	JOBS__MEDICAL_HEALTH("", "Medical Health"),
	JOBS__NONPROFITSECTOR("", "Non Profit Secotor"),
	JOBS__REALESTATE("", "Real Estate"),
	JOBS__RETAIL_WHOLESALE("", "Retail Wholesale"),
	JOBS__SALES_BIZDEV("", "Sales Biddev"),
	JOBS__SALON_SPA_FITNESS("", "Salon Spa Fitness"),
	JOBS__SECURITY("", "Securitz"),
	JOBS__SKILLEDTRADE_CRAFT("", "Skilled Trade Craft"),
	JOBS__SOFTWARE_QA_DBA("", "Software QA DBA"),
	JOBS__SYSTEMS_NETWORK("", "System Network"),
	JOBS__TECHNICALSUPPORT("", "Technical Support"),
	JOBS__TRANSPORT("", "Transport"),
	JOBS__TV_FILM_VIDEO("", "TV Film Video"),
	JOBS__WEB_INFODESIGN("", "Web Info Design"),
	JOBS__WRITING_EDITING("", "Writing Editing"),
	JOBS__ETC("", "Etc."),
	JOBS__PART_TIME("", "Part Time"),

	GIGS__CREW("", "Crew"),
	GIGS__EVENT("", "Event"),
	GIGS__LABOR("", "Labor"),
	GIGS__TALENT("", "Talent"),
	GIGS__COMPUTER("", "Computer"),
	GIGS__CREATIVE("", "Creative"),
	GIGS__DOMESTIC("", "Domestic"),
	GIGS__WRITING("", "Writing");
	
	static Logger logger = Logger.getLogger(CraigslistCategoryEnum.class);
	
	private String code; 
	private String name;
	
	/**
	 * Constructor.
	 * @param c
	 * @param n
	 */
	private CraigslistCategoryEnum(String c, String n) {   
		this.code = c; 
		this.name = n;
	}  
	
	/**
	 * GETTER.
	 * @return
	 */
	public String getCode() {   
		return this.code; 
	}

	/**
	 * GETTER.
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Compare the categorz codes with a input string.
	 * @param inputString
	 * @return
	 */
	public static CraigslistCategoryEnum getCategory(String inputString){
	    logger.debug("inputString="+inputString);
	    CraigslistCategoryEnum[] aValuesOfCraigslistCategoryEnum=CraigslistCategoryEnum.values();
	    
	    for (CraigslistCategoryEnum aFORCraigslistCategoryEnum : aValuesOfCraigslistCategoryEnum){
	        logger.debug("aFORCraigslistCategoryEnum.getCode()="+aFORCraigslistCategoryEnum.getCode());
	        
	        if (aFORCraigslistCategoryEnum.getCode().compareTo(inputString)==0){
	            
	            return aFORCraigslistCategoryEnum;
	        }
	    }
	    
	    return null;
	}
}