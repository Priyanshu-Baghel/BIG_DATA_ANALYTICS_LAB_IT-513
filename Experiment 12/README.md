# Big Data Analytics Lab (IT-513)

## Experiment 12 – Population Data Visualization using Power BI (Donut Chart & Tree Map)

**Contributor:** Priyanshu Baghel
**Mentor:** Dr. Abhishek Narwaria, Assistant Professor

---

## Aim

To visualize population data using Power BI by creating a **Donut Chart** and then morphing it into a **Tree Map** to perform hierarchical analysis.

---

## Introduction

Power BI is a business intelligence and data visualization tool widely used for reporting and dashboard creation.

This experiment focuses on two important Power BI visual types:

### 1. Donut Chart

* Represents data as portions of a circular ring.
* Useful for showing the proportional distribution of categories.
* Highlights percentage contribution of each group.

### 2. Tree Map

* Uses nested rectangles to represent hierarchical or grouped data.
* Size of each rectangle corresponds to the value of the data item.
* Effective for representing categorized, multi-level datasets.

Power BI allows users to **morph** visualizations—switch from one visual to another without changing underlying data fields.
This helps explore different perspectives using the same dataset.

---

## Procedure

### Step 1: Prepare Dataset

Create a CSV file named **PopulationData.csv** containing:

* Country
* Continent
* Population (Millions)

Example entries:

```
China, Asia, 1400
India, Asia, 1380
USA, North America, 331
Brazil, South America, 213
Nigeria, Africa, 206
```

### Step 2: Load Data into Power BI Desktop

1. Open Power BI Desktop
2. Go to **Home → Get Data → Text/CSV**
3. Select `PopulationData.csv`
4. Click **Load**

---

### Step 3: Create Donut Chart

1. Select **Donut Chart** from the Visualizations pane.
2. Assign fields:

   * **Legend:** Country
   * **Values:** Population (Millions)
3. Add a chart title such as: *Population Distribution by Country*
4. Enable **Data Labels** to show population percentages.

---

### Step 4: Morph Donut Chart into Tree Map

1. Click to select the existing Donut Chart.
2. In the Visualizations pane, choose **Tree Map**.
3. Assign:

   * **Group:** Continent, Country
   * **Values:** Population (Millions)

This converts the visual while preserving the dataset.

---

### Step 5: Format Visualization

* Adjust colors in **Format → Data Colors**
* Add title: *World Population Tree Map*
* (Optional) Add filters using slicers (for example, by continent)

---

### Step 6: Save the Power BI Report

Save the file as:

```
Population_Visualization.pbix
```

---

## Output

The final output consists of:

### 1. Donut Chart

Displays proportion of global population contributed by each country.

### 2. Tree Map

Shows hierarchical representation of continents and countries along with their population sizes.

These visuals demonstrate two different perspectives of the same dataset: proportional view and hierarchical view.

---

## Conclusion

This experiment demonstrated how Power BI can be used to visualize population data through multiple chart types. By transitioning from a Donut Chart to a Tree Map, users can explore both proportional and hierarchical insights in the same dataset. Power BI’s interactive visual transformation capabilities allow flexible and dynamic analysis of real-world data.

---
