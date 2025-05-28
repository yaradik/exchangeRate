# Exchange Rate Telegram Bot

A simple Java-based Telegram bot that provides current exchange rates for major currencies (USD, EUR, RUB, etc.).

This educational project demonstrates how to fetch external API data and interact with users via a Telegram bot.

## 🛠 Tech Stack

- Java
- Telegram Bot API (via [TelegramBots library](https://github.com/rubenlagus/TelegramBots))
- Exchange rate API (e.g. [exchangerate.host](https://exchangerate.host/) or similar)
- Maven

## 📦 Features

- Responds to `/start` and currency-related commands
- Fetches exchange rates in real-time from external API
- Parses and formats data for display in Telegram chat
- Can be extended to support multiple currency pairs

## 🚀 Getting Started

### 1. Clone the repo

bash
git clone https://github.com/yaradik/exchangeRate.git
cd exchangeRate

### 2. Set your Telegram bot token

Replace YOUR_BOT_TOKEN in the code with your actual Telegram token.

### 3. Run the bot

bash
mvn clean install
java -jar target/exchangeRate.jar
You may also need to configure an exchange rate API key depending on your provider.
