# CreateAggregatedStatistics-Analyst
I have build a software system that can be used by a analyst to be able to draw conclusions about energy consumption in relation to period of time.
An analyst would want to know the answers to questions such as (a) what period of the day his or her consumption
(b) at what time of day is the laundry room at its maximum (c) which period of the year / which month is the
heating consumption is minimum/maximum (d) the energy consumption in the kitchen changes
depending on the day of the week
Because we have measurements per second (so too many), analysts are interested in
statistics on these. We will limit ourselves to valuing (a) sum or (b)
average consumption per time period. So, for example, the analyst would
be able to request "for each month, an average consumption value for each of the 3 measurements
of the house". Or, as another example, "for each period of the day (see below), the total
consumption, for each of the 3 measurements of the house".

This system has 2 levels, a client piece that is responsible for
for the interaction with the end user and a server piece responsible for the
(a) the loading of the initial data, (b) the clustering and
computing statistics depending on what the analyst requests, and, (c) generating reports,
in more than one format, with the result of the calculation. On the server side
there are several packages, each with its own functionality. In each
package contains an interface.
