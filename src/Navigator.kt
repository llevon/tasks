interface Navigator {
    fun navigateTo(screen: Screen){
        screen.onStart()
    }
    fun finishApp(){
        println("Exiting program")
        System.exit(0)
    }

}