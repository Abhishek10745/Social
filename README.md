# 🧠 Social Media Sentiment Analysis using Java and Stanford CoreNLP

Welcome to my Java-based Social Media Sentiment Analysis project! 🚀  
This tool analyzes sentiment from tweets stored in a CSV file and visualizes the results using interactive JavaFX charts.

---

## 📌 Features

✅ Reads tweets from a structured CSV file  
✅ Performs sentiment analysis using **Stanford CoreNLP**  
✅ Displays:
- Console output with sentiment for each tweet  
- Pie chart and line chart showing sentiment distribution  
- Tweet-by-tweet sentiment breakdown in the JavaFX GUI  
✅ User-selectable tweet count for analysis  
✅ Calculates sentiment **percentages** and **average sentiment score**

---

## 🧱 Project Structure

SocialMediaSentimentAnalysis/
├── src/
│ └── com/
│ └── sentiment/
│ ├── Main.java # Entry point
│ ├── CSVReader.java # Reads tweets from CSV
│ ├── TweetSentimentAnalyzer.java# CoreNLP logic
│ └── SentimentDashboard.java # JavaFX charts and tweet viewer
├── resources/
│ └── twitter_dataset.csv # Tweet data file
├── lib/
│ ├── stanford-corenlp-.jar # CoreNLP jars
│ └── opencsv-.jar # OpenCSV jar
└── README.md

yaml
Copy
Edit

---

## 🧪 How It Works

1. The program asks you how many tweets you'd like to analyze.
2. It reads tweets from `twitter_dataset.csv`.
3. Sentiment is computed using Stanford CoreNLP (`Very Negative`, `Negative`, `Neutral`, `Positive`, `Very Positive`).
4. Each tweet and its sentiment is printed to the console.
5. The JavaFX dashboard opens with:
   - Pie chart of sentiment distribution
   - Line chart of sentiment trends
   - Scrollable list of individual tweet sentiments
6. You also get a summary with:
   - Total tweets analyzed
   - Count and percentage of each sentiment
   - Average sentiment score

---

## 📊 Sample Console Output

Tweet: I love this product!
Sentiment: Positive
Tweet: This is terrible and frustrating.
Sentiment: Very Negative
=== Overall Sentiment Summary ===
Neutral : 23 (38.33%)
Positive : 31 (51.67%)
Negative : 6 (10.00%)
Average Sentiment Score: 2.41
Tweet Count Analyzed : 60

yaml
Copy
Edit

---

## 📦 Requirements

- Java 11 or later
- [Stanford CoreNLP](https://stanfordnlp.github.io/CoreNLP/)
- OpenCSV (`opencsv-x.x.x.jar`)
- JavaFX SDK (added to project modules)

---

## 🚀 How to Run

1. Clone this repository  
2. Download and place required `.jar` files inside the `lib/` folder  
3. Import the project into IntelliJ IDEA (or any IDE)  
4. Make sure JavaFX libraries are configured properly  
5. Run `Main.java`

---

## 📈 Future Improvements

- Real-time tweet fetching via Twitter API  
- Exporting sentiment results to CSV  
- Bar chart/word cloud visualizations  
- Emotion classification (anger, joy, etc.)

---

## 🙋‍♂️ Author

**Abhishek Kumar**  
Aspiring software developer passionate about NLP, data science, and Java.
