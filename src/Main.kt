/**
 * Kotlin Collections Task 3 - Monkey Errors
 *
 *       OOOOO  OOO   OOO
 *          O  O   O O   O
 *         O   O   O O   O
 *       OOOOO  OOO   OOO
 * +-------------+-------------+
 * |    __v__    |     ___     |
 * |   ( o o )   |    /   |    |
 * |    (---)    |       _/    |
 * |    __|__    |      |      |
 * |   /|. .|\   |      o      |
 * +-------------+-------------+
 *
 * It seems that some of the zookeepers are not very
 * good at their job, and keep getting the cage numbers
 * muddled up. Can you add some error checking to the
 * system to help indicate when things go wrong?
 */


/**
 * Constant vales used to define some key values
 * which can then be used throughout the code...
 */
const val NUMCAGES = 8      // The total number of cages
const val EMPTY = "---"     // Represents an empty cage


/**
 * Program entry point
 */
fun main() {
    //-------------------------------------------------
    println("Setting up the cages...")

    val cages = setupCages()

    showMonkeyCages(cages)
    println()

    //-------------------------------------------------
    println("Placing monkeys into cages...")

    placeMonkeyInCage(cages, 1, "Kevin")
    placeMonkeyInCage(cages, 8, "Sally")
    placeMonkeyInCage(cages, 4, "Pam")
    placeMonkeyInCage(cages, 3, "Samson")
    placeMonkeyInCage(cages, 5, "Frank")
    placeMonkeyInCage(cages, 6, "Jim")

    println()

    showMonkeyCages(cages)
    println("Monkeys: ${monkeyCount(cages)}")
    println("Empty: ${emptyCount(cages)}")
    println()

    //-------------------------------------------------
    println("Trying some invalid placements...")

    placeMonkeyInCage(cages, 0, "Nigel")
    placeMonkeyInCage(cages, NUMCAGES + 1, "Nigel")

    println()

    //-------------------------------------------------
    println("Trying some more invalid placements...")

    placeMonkeyInCage(cages, 2, "")
    placeMonkeyInCage(cages, 2, "        ")

    println()

    //-------------------------------------------------
    println("Trying to clear invalid cages...")

    clearCage(cages, 0)
    clearCage(cages, NUMCAGES + 1)

    println()

    //-------------------------------------------------
    println("Trying to do some invalid swaps...")

    swapCages(cages, 0, 0)
    swapCages(cages, 0, 1)
    swapCages(cages, 1, 0)

    swapCages(cages, NUMCAGES + 1, NUMCAGES + 1)
    swapCages(cages, NUMCAGES + 1, NUMCAGES)
    swapCages(cages, NUMCAGES, NUMCAGES + 1)
}

/**
 * Creates and returns a Mutable List, size NUMCAGES,
 * populated with strings representing empty cages
 */
fun setupCages(): MutableList<String> {
    val cageList = mutableListOf<String>()
    for (i in 1..NUMCAGES) cageList.add(EMPTY)
    return cageList
}


/**
 * Put a given monkey into the specified cage number (1...MAX)
 */
fun placeMonkeyInCage(cageList: MutableList<String>, cageNum: Int, name: String): String {
    if (cageNum < 1 || cageNum > NUMCAGES) {
        return "that cage is out of range"
    }
    if (cageNum != null){
        return "please put in a cage"
    } //or if it is a good cage
    println("+++ Putting $name into cage $cageNum")
    cageList[cageNum -  1] = name
    return "Successfully placed $name in $cageNum"
}


/**
 * Display a list of all cages in the given list in the format:
 *
 * CAGES
 * - Cage 1: Kevin
 * - Cage 2: ---
 * - Cage 3: Samson
 * - Cage 4: Pam
 * - Etc.
 */

fun listAllCages(cageList: List<String>) {
    println("CAGES")
    for ((cageNum, name) in cageList.withIndex()) {
        println(" - Cage ${cageNum +1}: $name")
    }
}


/**
 * Display a list of all monkeys found in the given cage list:
 *
 * MONKEYS
 * - Kevin
 * - Samson
 * - Pam
 * - Etc.
 */
fun listAllMonkeys(cageList: List<String>) {
    println("MONKEYS")
    for ((cageNum, name) in cageList.withIndex()) {
        if (name != EMPTY) {
            println(" - $name")
        }
    }}


/**
 * Display a list of all empty cages in the given cage list:
 *
 * EMPTY CAGES
 * - Cage 2
 * - Cage 7
 * - Etc.
 */
fun listEmptyCages(cageList: List<String>) {
    println("EMPTY CAGES")
    for ((cageNum, name) in cageList.withIndex()) {
        if (name == "---") {
            println(" - Cage ${cageNum +1}")
        }
    }
}






/**
 * Display a full list of all monkeys and the cages they are in.
 * The names and cage numbers should line up in neat columns:
 *
 * MONKEYS & CAGES
 * - Kevin  (Cage 1)
 * - Samson (Cage 3)
 * - Pam    (Cage 4)
 * - Etc.
 *
 * Tip: the String.padEnd(N) function will help you here
 */
fun listAllMonkeysAndCages(cageList: List<String>) {
    println("MONKEYS & CAGES")
    for ((cageNum, name) in cageList.withIndex()) {
        if (name != EMPTY) {
            println(" - $name (Cage ${cageNum +1}) ")
        }
    }
}


/**
 * Returns the number of monkeys found in the given cage list
 */


fun monkeyCount(cageList: List<String>): Int {
    var numMonkey = 0
    for (name in cageList) {
        if (name != EMPTY) {
            numMonkey++
        }
    }
    return numMonkey
}


/**
 * Returns the number of cages that are empty in the given cage list
 */
fun emptyCount(cageList: List<String>): Int {
    var numEmpty = 0
    for ((cageNum , name) in cageList.withIndex()) {
        if (name == EMPTY) {
            numEmpty++
        }
    }
    return numEmpty
}


/**
 * Show all cages from the given list, formatted as a horizontal table:
 *
 * +--------+--------+--------+--------+----
 * | Cage 1 | Cage 2 | Cage 3 | Cage 4 | Etc.
 * +--------+--------+--------+--------+----
 * | Kevin  | ---    | Samson | Pam    | Etc.
 * +--------+--------+--------+--------+----
 *
 * Tip: the String.padEnd(N) function will help you here
 */
fun showMonkeyCages(cageList: List<String>) { //Muchos gracias ben for the leg up :saltuing_face:
    println() //row one
    for (i in cageList.indices) {
        print("+--------")
    }
    print("+")

    var cageNumber = 1 //row 2
    println()
    for (i in cageList.withIndex()) {
        print("| Cage $cageNumber ")
        cageNumber++
    }
    print("|")

    println() //row one
    for (i in cageList.indices) {
        print("+--------")
    }
    print("+")

    println()
    for ((i, cageNum) in cageList.withIndex()) {
        print("| ${cageNum.padEnd(7, ' ')}")
    }
    print("|")
    println()
    for (i in cageList.indices) {
        print("+--------")
    }
    print("+")


}


/**
 * Make a given cage empty (if a monkey was in it, it's gone now!)
 */
fun clearCage(cageList: MutableList<String>, cageNum: Int): String {
    if (cageNum < 1 || cageNum > NUMCAGES) {
        return "that cage is out of range"
    }
    if (cageNum != null){
        return "please put in a cage"
    } //or if the cage is working
    println("--- Clearing cage $cageNum")
    // REPLACE THIS WITH YOUR CODE!
    cageList[cageNum -  1] = EMPTY
    return "Successfully cleared cage"
}


/**
 * Swap the contents of two given cages.
 *
 * If one was full and the other empty, then the monkey just swaps
 * into the empty cage.
 */
fun swapCages(cageList: MutableList<String>, cageNum1: Int, cageNum2: Int): String {
    if (cageNum1 < 1 || cageNum1 > NUMCAGES) {
        return "Cage num 1 is not a proper cage"
    }
    if (cageNum2 < 1 || cageNum2 > NUMCAGES) {
        return "Cage num 2 is not a proper cage"
    } //else if it's all doing it and they're both workin  cages yep;
    println("<-> Swapping cages $cageNum1 and $cageNum2")
    var swappedMonkey = ""
    swappedMonkey = cageList[cageNum1 - 1]
    cageList[cageNum1 - 1] = cageList[cageNum2 - 1]
    cageList[cageNum2 - 1] = swappedMonkey
    return "Successfully swapped cage"
}






/**
 * ========================================================
 * ABOVE THIS COMMENT, PLACE A COPY OF ALL THE FUNCTIONS
 * YOU WORKED ON AND GOT WORKING IN TASK 2
 * ========================================================
 *
 * You will be modifying the following functions to add
 * error-checking:
 * - placeMonkeyInCage()
 * - clearCage()
 * - swapCages()
 *
 * If an error is detected, a suitable error message should
 * be shown such as:
 * - ERROR PLACING MONKEY: cage number 20 is out of range
 * - ERROR CLEARING CAGE: cage number 0 is out of range
 * - Etc.
 *
 * ========================================================
 */