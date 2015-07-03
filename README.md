# FlowDaggerMortar
A simple app (w/ comments and logs) showing MVP pattern using Square's Flow and Mortar. Derived mostly from Square's Flow and Mortar sample apps.
 - though there's no explicit use of Dagger shown yet. Not sure if I'll put it here or create a separate one, as the app might look "not simple enough" if I put all in one. Maybe I better change the name to MortarFlowSample.
I also used ButterKnife just to bind views to java from xml, instead of using findViewById.
 
If you want the simplest version, running but does nothing, Checkout the first commits. Use the logcat to see the sequence of events when you open the app, when you rotate, close the app, etc. You can also use breakpoint and debug so you can see more of what's happening.
I just used Log.e to easily see the logs.

I place in package 'squaresupport' those classes that I think would be better if included in the libraries either as part of the API itself (e.g. Layout, HandlesBack) or as templates/defaults (e.g. SimpleScreenContainer, GsonParceler).

Most of the codes are just boilerplates. Of course you wouldn't appreciate all those boilerplates yet because the app is too simple. You'll appreciate them more in a much more complicated app. Not sure but maybe for now I'll stop here, as going further might make the app not "simple (I think)" anymore. Maybe I (or anyone better) could create a separate app for that (for those who got the basics already), that would showcase how useful Mortar+Flow+Dagger is in a real world app. Square's Flow and Mortar examples are one, but really complicated (I still don't understand some parts), but good I think.

Yes, you would be asking why don't they just remove all those boilerplates and just make them work automagically.. I think that would make it more confusing and harder to read, should be balanced between boilerplate and magic.
Though I still hope they could improve on the boilerplates, via templates/defaults maybe? I think lots of experiments, experience, patience, open mind, and perseverance are needed to learn these libraries.

# Recommended way of learning.
Here are my recommendations on how you should begin to study these libraries so you can appreciate them and learn progressively.
Don't go straight to this app.
Study first what is dependency injection/inversion of control and MVP pattern. Also study the concept of singletons, garbage collection, and (something not common) using Contexts for scoping/retaining object instances (though I hope Google or Square could provide simple explanations/tutorials for that).
And then for the libraries. Study first what Dagger is (as Mortar is built on top of Dagger). And then after that, you can study Mortar and Flow simultaneously, because they really go hand in hand, if you're going to use one, better use them both. But be sure to understand and keep in mind the difference of their purposes. They work in tandem, but they're different.

Here are my suggested links (in my personal order):
Dagger and Dependency Injection:
http://www.infoq.com/presentations/Dagger
https://www.parleys.com/tutorial/architecting-android-applications-dagger or https://speakerdeck.com/jakewharton/android-apps-with-dagger-devoxx-2013
http://square.github.io/dagger/
http://antonioleiva.com/dependency-injection-android-dagger-part-1/
http://antonioleiva.com/dagger-android-part-2/
http://antonioleiva.com/dagger-3/
http://www.martinfowler.com/articles/injection.html (hardcore)
MVP:
https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter
http://www.infragistics.com/community/blogs/todd_snyder/archive/2007/10/17/mvc-or-mvp-pattern-whats-the-difference.aspx
Contexts:
https://possiblemobile.com/2013/06/context/
http://stackoverflow.com/questions/10347184/difference-and-when-to-use-getapplication-getapplicationcontext-getbasecon
http://stackoverflow.com/questions/1026973/android-whats-the-difference-between-the-various-methods-to-get-a-context
http://stackoverflow.com/questions/7298731/when-to-call-activity-context-or-application-context
Mortar and Flow:
https://youtu.be/R8NbpkpSuw8
http://corner.squareup.com/2014/10/advocating-against-android-fragments.html
https://corner.squareup.com/2014/01/mortar-and-flow.html
https://www.bignerdranch.com/blog/an-investigation-into-flow-and-mortar/

Then you can explore the sample apps:
https://github.com/square/mortar
https://github.com/square/flow
including this app
or make your own (better)

Then if you want, you can go further with Square libraries (I haven't yet):
https://github.com/JakeWharton/u2020
https://github.com/LiveTyping/u2020-mvp
https://github.com/lemonlabs/u2020-mortar


That's all for now. These are all just based on what I learned and I haven't really grasped 100% of it yet. So if you find anything wrong, or has better way of learning, please do contribute and educate us.


Thank you.
