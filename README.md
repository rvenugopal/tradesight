tradesight
==========

Clone the repository. In the project directory, launch sbt and then execute the `run` task

    ./sbt
    > run
  
After all the dependencies have been fetched and the sources compiled, the web app will be available at [http://localhost:8888/static/index.html](http://localhost:8888/static/index.html).

## Working the Problems

Most of the problems will probably be solved in `ProblemsPlan.scala`. This serves the endpoint to data for at least one of the problems. You are free to modify whatever you wish, and of course there some prepared utilities to help you on your way. 

## IDE Support

The SBT project includes both the [sbt-idea](https://github.com/mpeltonen/sbt-idea) and [sbteclipse](https://github.com/typesafehub/sbteclipse/) plugins out of the box.
To generate an Idea project, run the `gen-idea` task in the SBT shell. For Eclipse, run `eclipse`. For emacs et al, well, you know.