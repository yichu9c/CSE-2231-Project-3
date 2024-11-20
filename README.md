Project: Hashing Implementation of Map
Objectives
Familiarity with writing a kernel class for a new type and its kernel operations (Map layered on an array of Maps, using hashing).
Familiarity with developing and using specification-based test plans.
The Problem
The problem is to complete and carefully test implementations of all the constructors and kernel methods defined in interface MapKernel, building the data structure representing a Map object by layering it on top of an array of (perhaps surprisingly) Map itself. The algorithmic approach is to use hashing to immediately narrow every search to a much smaller Map.

There is nothing circular about this! The Map entries (buckets) in the array (hash-table) simply have a different implementation than the one you are writing code for here. When constructing the hash-table (in private method createNewRep), you will set each bucket to be a Map2 object. (You could use any other Map implementation other than Map4.)

The other interesting thing to note about this assignment is how little code you have to write for the bodies of the Map4 kernel methods. Hashing, in other words, offers a tremendous performance improvement for very little code. On the other hand, as in all kernel implementations, you have to get this code all correct, for if even one of the method bodies is wrong then other methods might appear not to work correctly during testing. This makes debugging a challenge. There are several reasons for this. First, the kernel methods have asserts to check the preconditions in order to prevent you from executing buggy test cases. These asserts call your own code to do the checking. So, for example, the first time you add something in a test case, the method starts by asking whether the key value is already defined in the Map object; and this means that, if your code for hasKey doesn't work correctly, you might get a violated assertion while appearing to test add. Second, if your test cases use either toString or equals to check the results, these methods can essentially call any of your kernel methods. Third, if your code for any method fails to make the representation invariant true at the time it returns, then the next method called in the test script might break; but the problem is actually with the previous method. Make sure you understand all of these difficulties so you can deal with them.

Setup
Only one member of the team should follow the steps to set up an Eclipse project for this assignment. The project should then be shared with the rest of the team by using the Subversion version control system as learned in the Version Control With Subversion lab.

To get started, import the project for this assignment, MapWithHashing, from the MapWithHashing.zip file available at this link. If you don't remember how to do this, see the Setup instructions in the previous project.

Method
Complete the bodies of the mod and createNewRep private methods, the two constructors, and the kernel methods (add, remove, removeAny, value, hasKey, and size) in Map4 in the src folder.
Develop a complete and systematic test plan for the MapKernel constructor and kernel methods and add your test cases at the end of MapTest in the test folder. Note that you should have already developed such a test plan for an earlier homework and lab. Just make sure you improve it as necessary to meet the standards of quality and completeness that have been discussed before.
Note that there are two other JUnit files in the test folder, Map4Test.java and Map4Test1009.java. Map4Test.java contains the usual class extending MapTest and calling the Map no-argument constructor in the implementation of the constructor method. However, Map4 declares another constructor that takes the hash table size as a parameter. To test this constructor and the kernel methods with a hash table size different from the default, Map4Test1009 extends MapTest and the constructor method calls this other constructor with a hash table of size 1009. When JUnit executes this new test fixture, it tests the new constructor and all the test cases defined in MapTest are tested with a hash table of size 1009. To test your implementation with any other hash table size, simply create a new JUnit fixture Map4Test### (where '###' is the size of the hash table) that extends MapTest and implement the constructor method by invoking the one-int-argument constructor with the chosen hash table size.

One last thing: in Eclipse you can run all the JUnit test fixtures in the project in one step by right clicking on the test folder in the Package Explorer view and selecting Run As > JUnit Test from the contextual menu.

When you and your teammate are done with the project, decide who is going to submit your solution. That team member should select the Eclipse project MapWithHashing (not just some of the files, but the whole project) containing the complete group submission, create a zip archive of it, and submit the zip archive to the Carmen dropbox for this project, as described in Submitting a Project. Note that you will only be allowed one submission per group, that is, your group can submit as many times as you want, but only the last submission will be on Carmen and will be graded. Under no circumstance will teammates be allowed to submit separate solutions. Make sure that you and your partner agree on what should be submitted.
Additional Activities
Here are some possible additional activities related to this project. Any extra work is strictly optional, for your own benefit, and will not directly affect your grade.

Copy and paste the following piece of code at the end of Map4 (after the iterator code and before the class closing '}').
    /*
     * Other methods (overridden for performance reasons) ---------------------
     */

    @Override
    public final V replaceValue(K key, V value) {
        assert key != null : "Violation of: key is not null";
        assert value != null : "Violation of: value is not null";
        assert this.hasKey(key) : "Violation of: key is in DOMAIN(this)";

        // TODO - fill in body

        // This line added just to make the component compilable.
        return null;
    }
Provide a non-layered implementation of the secondary method replaceValue defined in Map.

Add appropriate test cases for the replaceValue method to MapTest and test your implementation.
Replace the Map2 implementation with a different one among those available in package components.map and verify that your implementation of Map4 still passes all your test cases. Interestingly, you can even use the components.map.Map4 implementation because, although it has the same name as your implementation, it is in a different package and Java has no trouble distinguishing between the two.
Take a look at the Standard methods (newInstance, clear, and transferFrom) in Map4. We will discuss the details of these implementations later in the semester, but for now, design test cases to test these methods and add the test cases to your test fixture, MapTest.
