package Enum;

public enum Category {
    FOOD_DRINKS("Food & Drinks", new String[]{"Breakfast", "Lunch", "Snacks", "Dinner"}),
    SHOPPING("Shopping", new String[]{"Clothes", "Tech & Electronics", "Gifts", "Other", "Groceries"}),
    HOUSING("Housing", new String[]{"Utilities", "Mortgage & Rent", "Maintenance"}),
    BILLS("Bills", new String[]{"Mobile", "Internet", "Subscriptions", "Taxes", "Other"}),
    TRANSPORT("Transport", new String[]{"Public", "Auto"}),
    VEHICLE("Vehicle", new String[]{"Gas", "Parking", "Maintenance", "Insurance"}),
    LIFESTYLE("LifeStyle", new String[]{"Entertainment", "Healthcare", "Sports", "Hobbies", "Trips & Holidays", "Haircut"}),
    OTHER("Other", new String[]{}),
    LEND("Lend", new String[]{});

    private final String displayName;
    private final String[] subCategories;

    Category(String displayName, String[] subCategories){
        this.displayName = displayName;
        this.subCategories = subCategories;
    }

    public String getDisplayName(){
        return displayName;
    }
    public String[] getSubCategories(){
        return subCategories;
    }

}
