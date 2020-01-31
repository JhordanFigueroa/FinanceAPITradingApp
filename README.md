# UPENN591FinalProject
# University of Pennsylvania MCIT Online 591 FINAL PROJECT 
## GROUP 30 - Financiers
### Alex Ilgenfritz, Chris Payne, Jhordan Figueroa

# Disclaimer 
This application and technical analysis is meant for informational purposes only, its not intended to serve 
as a recommendation to buy or sell any security in any account, and its not an offer or sale of a security. 
This is not intended to serve as the basis for any investment decision. 

### Start Project
To start the project go to ProjectRunner class and click on the main method to run the program. 

### GitHub Repository Link 
https://github.com/JhordanFigueroa/UPENN591FinalProject

## Purpose 
This is the final project for the Fall 2019 UPENN MCIT 591. The purpose of the program, wherein the primary controller 
is found in the ProjectRunner.java class, is to obtain live market quotes from the "Investor's Exchange" free API, 
make notional trades depending on an indicator called the stochastic oscillator, and display the results as well search 
for any security in a user-friendly interface. This APP allows the user to search for ETF's or Stocks. Such as 
COST(Costco), AAPL(Apple), TSLA(Tesla), VOO(Vanguard Exchange Trade). The search feature won't work for all securities. 

## Tech Stack 
This project was built using Java, Swing(GUI), and financial data API. The API we used was from https://iexcloud.io/. 

## Features 
Display Technical Analysis for user via a "DisplayTrade" Button, green means execute trade, red means don't execute 
trade. Search Field for any security. Add Button to add security to list. Remove Button to remove security from list. 

## Description
There are three main elements comprising this project:

1. the user interface (UI),
2. a class built to obtain the stochastic oscillator (a specific indicator) of a given quote,and
3. an API caller (retrieves live quotes).

The user interface (UserInterface.java) has several options:

1. a search field,
2. “Add” button,
3. “Remove” button,
3. list with scroll panel for securities, and
4. a "Display Trades" button.

The "Display Trades" button checks the ETFs available and decides whether or not to trade and displays the results, 
green means the trade is suggested to be executed and red means that the trade is not suggested to be executed. The 
search field allows one to search for a specific ETF/Stock. The “Add” button allows the user to add the security to 
the list. The “Remove” button allows the user to remove the security from the list. The StochasticOscillator.java class 
has a singular function: determine the result of an equation designed to retrieve the stochastic oscillator for a given
quote. It does this via a method called "calcStochasticOscillation.

The API caller has several classes:

1. URLTranslator.java,
2. WebsiteDataReader.java,
3. Quote.java, and
4. AutoTrader.java.

The purpose of the URLTranslator is to stitch together the various URL components required for a free, live quote by 
passing the ETF (Exchange Traded Fund in question, a.k.a. the stock ticker) to request the "high", "low", "open", 
and "close" prices. This class accomplishes this via two methods: "setSandbox" and "convert". "setSandbox" allows 
one to test the class by using a specialized test call to the API, and "convert" uses the ETF given to generate the 
appropriate URL. An instance of this class is created in the WebsiteDataReader.java class, whose purpose is to use 
this URL to physically connect to the website and retrieve the required data. It does this with another two methods: 
"getData" and "readConnection". "readConnection" merely connects to the URL given by the URLTranslator and returns 
the text received. "getData" uses this result by passing it to the Quote via creation of one. Additionally, it sets 
the ETF variable in the same quote. Quotes (Quote.java) represent information on an ETF at a particular time frame. 
This class includes a high, low, open, and close price; the ETF it represents, a "k" variable that represents the 
stochastic oscillator, and a String called "csvText" that stores comma delimited information on the quote. The Quote 
has several methods, mostly getters and setters, but of interest are the constructor (takes a String of output text 
given by the WebsiteDataReader and converts its information into the variables of the class, as well as sets the 
results given by the StochasticOscillator.java class to "k") and the overridden "toString," which prints the Quote's 
ETF and high, low, open, and close variables. The AutoTrader.java class utilizes the WebsiteDataReader to retrieve 
these quotes, which it stores in ArrayLists organized by the ETF they belong to. This class also stores the thresholds 
for which an acceptable trade can be made (based on the stochastic oscillator) as doubles organized by ETF. It is 
written to support repetitive trading on its own accord in the main method, which utilizes the "decideTrade" method. 
This method merely returns whether or not the stochastic oscillator is higher than the tolerance as defined earlier, 
and then notionally makes a trade based on this result. The last method in the AutoTrader class, "saveData," merely 
creates a csv file and prints the trades made, organized by ETF.
 
 

