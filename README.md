# Split-is-Fun
# Splitting bills with friends and family can be stressful or awkward. Split is Fun app help roommates, travelers, couples, and friends organize their shared expenses so everyone knows who owes who.

# Working of the App:
https://user-images.githubusercontent.com/74361880/144001993-a165c7f6-3935-4e5d-9cb6-ea1046ba0809.mp4

## The Welcome screen layout

The first screen displayed when the app is launched, is the welcome page screen as shown below, where we can either add or view group. 

<img src="https://user-images.githubusercontent.com/74361880/143997533-72cd7e25-7771-4fc7-a88d-74baca94822a.jpg" width="40%" />


## Create New Group and View Them

You can create new group by clicking on the create new group view and then can view them.

<img src="https://user-images.githubusercontent.com/74361880/143998016-eaf6bb4d-afa2-4523-8a42-608e1b530f45.jpg" width="40%"> <img src="https://user-images.githubusercontent.com/74361880/143998361-5cc120ef-c4d3-4300-9858-6fc8174d0345.jpg" width="40%"> 


## Add Members 

You can add new members to the group by clicking on the "+" button and then it will be visible.

<img src="https://user-images.githubusercontent.com/74361880/143998641-4b8522e3-2794-4d07-a474-485d5d798054.jpg" width="40%"> <img src="https://user-images.githubusercontent.com/74361880/144000092-04a09e17-b1eb-4a6e-ba22-35d09892991b.jpg" width="40%"> 

## Add Expenses 

You can add the expenses for the group by clicking on the "+" button and then they will be visible.

<img src="https://user-images.githubusercontent.com/74361880/144000244-438fec53-dbfb-47f6-a364-8448d2569747.jpg" width="40%"> <img src="https://user-images.githubusercontent.com/74361880/144000260-e682a0d4-2c04-4551-8906-400de8a12765.jpg" width="40%"> 

## View balances and dues 

You can view who owes whom and who is to pay and who is to be paid.

<img src="https://user-images.githubusercontent.com/74361880/144000426-ac119876-ca03-4493-a42e-20513522d4d2.jpg" width="40%" />

# Features:
* Offline app
* No user login and registration required
* Uses a O(n) algorithm to settle debts
* Built in Java for Android Platform

# Android concepts used:
* Recycler views
* Fragments & ViewPager
* Activites & intents
* Room Persistence, LiveData, View-Model
* Different types of layouts

## Database Design:
We use three simple entities:
<img src="https://github.com/nishant-boro/split-it-easy-android/blob/master/db_view.png" width="800">

Hence, if a group is deleted/updated the associated bills and members of the group are deleted/updated too. 
Also, if a member is deleted/updated the associated bills of the member are deleted/updated too.

Room persistence library with View-Models and LiveData is used in this project. The different levels that any database operation goes through are:

ViewModelFactory(entry point) \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| \
ViewModel \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| \
Repository(Perform Queries in Async Mode) \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| \
Data access Object(Queries to insert,delete,..) \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| \
Entity(columns)


## Algorithm used for settling debts:

Calculate Balances: Find the balance of everyone in the group. Balance is the net amount of money someone owes or is owed from the group. Ex:
### Initially: 
Member Expenses(Contributions made by the member) \
----------- --------- \
A&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;500 \
B&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;600 \
C&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;200 \
Total&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1300

Divide Total/noOfMembers = 1300/3=433.33(let's call this as eachPay). Hence, each member in the group was supposed to spend an expense of 433.33 bucks. Next, for each individual, subtract expenses(of the member) from eachPay. Ex: For member A, balance = eachPay - expenses of the member=433.33-500=-66.67. If the balance turns out to be -ve, it means the individual is owed money and hence added to the debtors list. If the balance turns out to be +ve, it means the individual owes money to the group and hence added to the creditors list. \

### Final Balances:
Member Balances \
----------- --------- \
A&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-66.67  
B&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-166.67\
C&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;233.33

Calculate Transactions: Pick the largest elements from the debtors and creditors list. Ex: If debtors = {4,3} and creditors={2,7}, pick 4 as the largest debtor and 7 as the largest creditor.Now, do a transaction between them. The debtor with a balance of 4 receives $4 from the creditor with a balance of 7 and hence, the debtor is eliminated from further transactions. Repeat the same steps until and unless there are no creditors and debtors.

Optimisation: This algorithm produces correct results but the no of transactions is not minimum. To minimize it, we could use the subset sum algorithm which is a NP problem. The use of a NP solution could really slow down the app!





