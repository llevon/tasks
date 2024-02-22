import kotlin.concurrent.thread

data class Profile(val id: Int, val name: String, val accountInfo: String)

class ProfileManager {
    private val profiles = mutableListOf<Profile>()
    fun addProfile(profile: Profile) {
        profiles.add(profile)
    }

    fun displayProfiles() {
        println("Account List:")
        profiles.forEach { println("${it.id}. ${it.name}") }
    }

    fun displayAccountInfo(profileId: Int) {
        val profile = profiles.find { it.id == profileId }
        if (profile != null) {
            Thread.sleep(2000)
            println("Account Info for ${profile.name}: ${profile.accountInfo}")
            Thread.sleep(2000)
        } else {
            println("Profile not found.")
        }
    }
}

fun main() {
    val profileManager = ProfileManager()
    profileManager.addProfile(Profile(1, "John D", "Account details for John"))
    profileManager.addProfile(Profile(2, "Levon Kh", "Account details for Levon"))
    profileManager.addProfile(Profile(3, "Joe S", "Account details for Joe"))
    profileManager.addProfile(Profile(4, "Robert P", "Account details for Robert"))
    profileManager.addProfile(Profile(5, "Mary C", "Account details for Mary"))
    profileManager.addProfile(Profile(6, "Joseph D", "Account details for Joseph"))
    while (true) {
        profileManager.displayProfiles()
        println("Enter profile number to view account info, or 'q' to quit:")
        val input = readlnOrNull()

        if (input.equals("q", ignoreCase = true)) {
            break
        }
        try {
            val profileId = input?.toInt()
            if (profileId != null) {
                profileManager.displayAccountInfo(profileId)
            } else {
                println("Invalid input. Please enter a valid profile number.")
            }
        } catch (e: NumberFormatException) {
            println("Invalid input. Please enter a valid profile number.")
        }
    }
    println("Exiting program.")
}
