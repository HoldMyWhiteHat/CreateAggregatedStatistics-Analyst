System Overview

The system developed is designed to assist analysts in drawing conclusions about energy consumption over different periods of time. It enables users to explore patterns and identify trends in household energy usage.

Analysts may want to answer questions such as:
(a) During which period of the day energy consumption is highest or lowest.

(b) At what time of day the laundry room reaches its peak energy usage.

(c) Which month or period of the year heating consumption is at its minimum or maximum.

(d) Whether energy consumption in the kitchen varies depending on the day of the week.

Since energy measurements are recorded every second, the dataset is extremely large. Therefore, analysts are primarily interested in aggregated statistics rather than individual measurements. The system focuses on calculating two types of statistical values for a given time period:

1.Total (sum) energy consumption

2.Average energy consumption

For example, an analyst may request:

-“For each month, calculate the average consumption for each of the three household measurements.”

-“For each period of the day, calculate the total consumption for each of the three household measurements.”

System Architecture

The system follows a two-tier architecture:

1.Client Layer
Responsible for user interaction. It allows analysts to submit queries, select time periods, and request specific statistical reports.

2.Server Layer
Responsible for the main processing tasks, including:
a.Loading and managing the initial dataset

b.Aggregating and computing statistics based on the analyst’s request

c.Generating reports in multiple output formats that present the results of the analysis

Server-Side Modular Design

The server side of the system is structured into several packages, each responsible for a specific functionality of the application. This modular design improves maintainability, scalability, and separation of concerns.

Each package defines a corresponding interface that specifies the operations and services provided by that module. The use of interfaces promotes loose coupling between system components and facilitates future extensions or modifications of the system architecture.
