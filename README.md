# Foreign Exchange Api

This API is implemented to retrieve currency exchange rates, create currency conversion and list currency conversions. It uses fixer.io fx rate service provider to retrieve currency exchange rates.

## Rest Endpoints

Retrieve Exchange Rate (GET): Gets base currency and target currency as request parameters, and returns exchange rate in response body. Due to constraints of fx rate service provider service plan, only "EUR" can be used as base currency. Base currency is "EUR" by default and it is not required.

Create Currency Conversion (POST): Gets source currency, source amount, and target currency parameters in request body, and returns amount in target currency and currency conversion transaction id in response. Again, source currency is not a required field and it is "EUR" by default.

List Currency Conversions (GET): It can be run without passing any request parameters or it can be run by passing some or all of the following parameters: transaction id, transaction creation date, page, and size. In response, it returns page, size, total count of retrieved currency conversion transactions, and a list of currency conversion transactions.
Currency conversion transaction has following fields: transaction id, creation date, source currency, target currency, source amount and amount in target currency.
 