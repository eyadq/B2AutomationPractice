package app.vercel.test.utilities.enums;

public class TestingEnums {

    public static void main(String[] args) {

        /*
           Enums are just sets of constants in their own class.
           If enum is declared public, must be in its own file
           Use constant naming convention for naming values

           .name()     ---> String: gives the value
           .values()   ---> Array: gives ALL values as array; USE FOR ITERATING

         */

        /*
            We can have values with no parameters if we want or give no parameters for basic enum
            Enums must be in own file if declared public. Otherwise, they can be "inner class' in same file
            Here are two enums declared inside of the main method:
        */

        enum Colors{
            RED,
            BLUE,
            YELLOW
        }

        //Enums are classes. They can have constructors, methods (usually getters), instance variables,
        enum countriesInNorthAmerica{
            CANADA("North", "English"),
            UNITED_STATES_OF_AMERICA("Center", "English"),
            MEXICO("South", "Spanish");
            private String location;
            private String officialLanguage;

            private countriesInNorthAmerica(String location, String officialLanguage){
                this.location = location;
                this.officialLanguage = officialLanguage;
            }

            public String getLocation() {
                return location;
            }

            public String getOfficialLanguage() {
                return officialLanguage;
            }


        }


        //This is single entry of the enums
        //Enum_Type    Reference =  Enum_Type.VALUE
        DaysOfWeek dayOfTomorrow = DaysOfWeek.SUNDAY;
        States ohio = States.OH;
        Colors myFavoriteColor = Colors.BLUE;
        countriesInNorthAmerica usa = countriesInNorthAmerica.UNITED_STATES_OF_AMERICA;

        //Really good for use as constants
        if(dayOfTomorrow.equals(DaysOfWeek.FRIDAY)){

        } else {
            System.out.println("Sunday and Friday are not the same.");
        }

        // .name() is built into Enum
        String str = Colors.BLUE.name();
        System.out.println(str); //BLUE

        // .name() is Enum method, gives the value BUT NOT parameters
        System.out.println(ohio.name()); //OH

        // Use GETTERS to get the parameter for value
        System.out.println(States.AK.getStateName()); //Alaska

        if(usa.getOfficialLanguage().equals("French")){
            System.out.println("This country speaks French");
        } else {
            System.out.println("This coountry does not speak French, it speaks " + usa.getOfficialLanguage());
        }

        // .values() returns array with all values; which we need for iterating
        States[] states = States.values();


        System.out.println("Here are the codes for all 50 states");
        for (States state : states){
            System.out.println(state.name());
        }

        System.out.println();

        System.out.println("Here are the names for all 50 states");
        for (States state : states){
            System.out.println(state.getStateName());
        }
    }
}
