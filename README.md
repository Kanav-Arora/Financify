# Financify

[![Hits-of-Code](https://hitsofcode.com/github/Kanav-Arora/Financify?branch=main)](https://hitsofcode.com/github/Kanav-Arora/Financify/view?branch=main)

![GitHub forks](https://img.shields.io/github/forks/Kanav-Arora/Financify?style=social)

![Github stars](https://img.shields.io/github/stars/Kanav-Arora/Financify?style=social)

We are proposing an idea to make an accounting software using Java programming language and MySQL database. The desktop application will have a user-friendly UI and a database to keep track of all records.

# Financify
![image](./EndSem/src/Icons/icon.png)
![image](./EndSem/src/Icons/full_logo.png)
>## *Redifining Accounting*

*This readme file is being made to introduce and explain our project, *‘Financify’* to a large number of developers and users all around the globe. It contains the information regarding the full framework and the purpose of the project.*

**The whole project is based on Java programming language.The desktop application has a user-friendly UI and a very famous and stable database named MySQL to keep a track of all records.*’Financify’* is designed in such a way that it will meet the demands of a small scale business and as well as a large scale business.The implementation of the front-end for this project was done using Java Swing library for building the GUI of the software, along with this NetBeans IDE (Integrated Development Environment) and VS Code ( Code Editor ) are the tools which were used to compile all distinguished and vast frames of this project. Keeping all the bills and vouchers in an organised order is no more a hectic task,*’Financify’* makes it easy for you.**  
____


># INDEX
- [Frames and Description](#id-sec1)
- [ER Diagram](#id-sec2)
- [DB DIagram](#id-sec3)
- [Libraries](#id-sec4)
- [Installation Guide](#id-sec5)
- [Contributors](#id-sec6)

<div id='id-sec1'/>


>## FRAMES AND DESCRIPTION

*In this section, the whole framework will be described in an elaborated and a sequential way which will help the users to know more about its working and functionality and any person will be able to use it with the utmost usability* -:
 
### Register Page:



<p> Now as the application is launched, the first window which appears in front of the user is the register page, which will ask the user to enter his/her respective details in order to complete the registration process and create a new account which will be used in the future. On completing the registration process, a pop up window appears which confirms that an account has been created successfully. The Register page comprises of various text fields and combo boxes which are:<>
Name: Should not contain any digits or any other special characters (only alphabets).
Gender: Users can choose the gender as per their choice.(combo box). (Mandatory field)
Username: The users can keep the username according to their comfort but have to remember it as it will be asked at the time of logging in. The username has to be distinct for each account in order to avoid any ambiguities(Can contain alphanumeric characters as well as special symbols
Email: Can be upto 40 characters in length comprising of alphanumeric and special symbols (has to be in the format @xyz.com)(also has to be distinct for each account)
Password: Contains alphanumeric characters, one upper case character and a special symbol. Length can be from 8 - 20 characters. ( cannot be the same for any user).
 At the end, there is a Jbutton for confirmation and creating the account and an option to directly go to the login page if an account already exists.
Regular Expression (Regex) was used in the backend code of this frame to ensure that the users enter the data according to the constraints provided above.
    
### Login Page:


  It is the page through which a user successfully logs in to their respective account and can actually start using the software and it also comprises mainly of two text fields (just like any other ordinary login page) that is the Username and the Password with very same constraints for these two fields as they are in the case of the registration window. There is also an option that if the user does not have an existing account then they can come back to the registration page accordingly.


## Main Frame:


After the user has successfully logged in, the main frame appears which showcases the summary and overview of some of the transactions and dues for the person’s account. There is a proper separate panel which has labels for all the necessary frames which include details such as Items, Ledger Accounts, Trial Balancing, Sale, Purchase and Voucher. By simply clicking on any of these above-mentioned labels, the user will be brought to a new window which will show all the required account details for the respective label which was clicked by the user. A table with proper columns for Bill No., Account Name, Due Date and Amount is present in the frame for the user to get a summary. Similarly, a graphical representation has also been provided to get an overview of the account and the transactions made so far which is organised well on the basis of segregating the amount for each and every month. The user will also have options for signing out of their account as well as for making an exit from the application.
Items- This frame is made to enter and edit details related to the items which the user wants to keep the record of. The user can simply view and edit the details of the existing items, add new items and can even delete items and their details according to their desire. The exit button is present for the user. By clicking on it, the user will be brought back to the main frame window.
Ledger Accounts: As the name suggests, this frame is a complete receptacle and a display to all your details and transactions which are ongoing in your firm. The ledger accounts frame consists many options to assist you in keeping a proper and organised record of all your transactions-:
            -> Setting of Date and Time Period of Transactions- There is an option provided in the software which helps the user to set the date (from and till) you want to know transactions from. Checkboxes are provided to directly select the time period on a daily, monthly and quarterly basis which makes it easier for the user to retrieve the required data from the records.
         -> Transaction Table: It shows the serial number, particulars, exact date of billing/transaction, amount and type( credit or debit) which can also be downloaded in excel format by the user.
       -> Net Balance Display: These boxes show the net balance, amount credited and debited in the account of the particular person you want to see.
At the end, the user can move to the account setup frame or exit the current frame according to their choice.        
-> Account Setup : This frame allows the user to create a new account, edit it or delete an account according to the user’s discretion. It contains options/text fields to enter/store/display details related to a person such as their ID, Name, Type, Address, State,Pincode,Email,GST and many other details related to banking.
At the end, there are buttons for the above tasks which one can perform in the frame or exit the frame if they want. 


<div id='id-sec1'/>

<div id='id-sec2'/>


> ## ER Diagram



<div id='id-sec3'/>


>## DB Diagram
<div id='id-sec4'/>


>## Libraries
<div id='id-sec5'/>


>## Installation Guide
<div id='id-sec6'/>


>## Contributors

<p>We are a Quintet. The A-Team includes Shivam Bhatt, Kanav Arora, Kinshuk G. Pandey, Samriddh Srivastava and Arnav Batra. Now when it comes down to individual contributions, we would like to mention a brief summary that will explain the way in which this project was developed. Backend coding and logic development is one thing which almost all of us did. Designing and presentation was handled by Kinshuk, Arnav and Samriddh. Shivam and Kanav handled the GUI integration and major frame development. Debugging and testing was done by Shivam, Samriddh and Kinshuk. Also a special mention to Arnav, the credit goes to him for our logo design. Kanav and Shivam were responsible for significant project development and the merging of frames. Kinshuk, Samriddh and Arnav took charge of the documentation and project planning.<p>



<br>

# License

This project comes under [MIT License](LICENSE).

<br>

# Contribution Guide

Check the [contribution guide](CONTRIBUTING.md).
