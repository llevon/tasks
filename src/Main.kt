import Screen.Profiles.profileManager
sealed class Screen : Navigator {
    abstract fun onStart()
    object Profiles : Screen() {
        private val profileManager = ProfileManager()

        override fun onStart() {
            while (true) {
                displayProfiles(profileManager.profiles)
                println("Enter profile number to view account info, 'q' to quit, 'a' for adding an account:")
                val input = readlnOrNull()
                when {
                    input.equals("q", ignoreCase = true) -> {
                        finishApp()
                    }

                    input.equals("a", ignoreCase = true) -> {
                        navigateTo(AddProfile(profileManager))
                    }
                    else -> {
                        try {
                            val profileId = input?.toInt()
                            if (profileId != null) {
                                navigateTo(Details(profileManager, profileId))
                            } else {
                                println("Invalid input. Please enter a valid profile number.")
                            }
                        } catch (e: NumberFormatException) {
                            println("Invalid input. Please enter a valid profile number.")
                        }
                    }
                }
            }
        }
        private fun displayProfiles(profiles: List<Profile>) {
            println("Account List:")
            profiles.forEach { println("${it.id}. ${it.name}") }
        }
    }
    class AddProfile(private val profileManager: ProfileManager) : Screen() {
        override fun onStart() {
            println("Enter new profile details:")
            println("ID:")
            val id = readlnOrNull()?.toIntOrNull()

            println("Name:")
            val name = readlnOrNull()

            println("Account Info:")
            val accountInfo = readlnOrNull()

            if (id != null && name != null && accountInfo != null) {
                if (profileManager.profiles.any { it.id == id }) {
                    println("Error adding profile: Profile with ID $id already exists.")
                } else {
                    val newProfile = Profile(id, name, accountInfo)
                    try {
                        profileManager.addProfile(newProfile)
                        println("Profile added successfully!")
                    } catch (e: Exception) {
                        println("Error adding profile: ${e.message}")
                    }
                }
            } else {
                println("Invalid input. Please enter valid details.")
            }
        }
    }
    class Details(private val profileManager: ProfileManager, val id: Int) : Screen() {
        override fun onStart() {
            displayAccountInfo(profileManager.profiles, id)
            println("Press 'i' to go back to profile list, 'q' to quit:")
            while (true) {
                val input = readlnOrNull()
                when {
                    input.equals("i", ignoreCase = true) -> {
                        break
                    }

                    input.equals("q", ignoreCase = true) -> {
                        finishApp()
                    }
                }
            }
        }
        private fun displayAccountInfo(profiles: List<Profile>, profileId: Int) {
            val profile = profiles.find { it.id == profileId }
            if (profile != null) {
                Thread.sleep(2000)
                println("Account Info for ${profile.name}: ${profile.accountInfo}")
            } else {
                println("Profile not found.")
            }
        }
    }
}
fun main() {
    Screen.Profiles.onStart()
}
