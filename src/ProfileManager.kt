class ProfileManager {
    val profiles = mutableListOf<Profile>().apply {
        add(Profile(1, "John D", "Account details for John"))
        add(Profile(2, "Levon Kh", "Account details for Levon"))
        add(Profile(3, "Joe S", "Account details for Joe"))
        add(Profile(4, "Robert P", "Account details for Robert"))
        add(Profile(5, "Mary C", "Account details for Mary"))
        add(Profile(6, "Joseph D", "Account details for Joseph"))
    }
    fun addProfile(profile: Profile) {
        profiles.add(profile)
    }
}