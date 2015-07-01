# FlowDaggerMortar
A simple app (w/ comments and logs) showing MVP pattern using Square's Flow and Mortar
 - though there's no explicit use of Dagger shown yet. Not sure if I'll put it here or create a separate one, as the app might look "not simple enough" if I put all in one. Maybe I better change the name to MortarFlow.
I also used ButterKnife to bind views from xml.
 
If you want the simplest version, running but does nothing, Checkout the first commits. Use the logcat to see the sequence of events when you open the app, when you rotate, close the app, etc.
I just used Log.e to easily see the logs.

I place in package 'squaresupport' those classes that I think would be better if included in the libraries either as part of the API itself (e.g. Layout, HandlesBack) or as templates/defaults (e.g. SimpleScreenContainer, GsonParceler).

Most of the codes are just boilerplates. Of course you wouldn't appreciate all those boilerplates yet because the app is too simple. You'll appreciate them more in a much more complicated app. Not sure but maybe for now I'll stop here, as going further might make the app not "simple (I think)" anymore. Maybe I (or anyone better) could create a separate app for that (for those who got the basics already), that would showcase how useful Mortar+Flow+Dagger is in a real world app. Square's Flow and Mortar examples are one, but really complicated (I still don't understand some parts), but good I think.

Yes, you would be asking why don't they just remove all those boilerplates and just make them work automagically.. I think that would make it more confusing and harder to read, should be balanced between boilerplate and magic.
Though I still hope they could improve on the boilerplates, via templates/defaults maybe? I think lots of experiments, experience, patience, open mind, and perseverance are needed to learn these libraries.

That's all for now.



Thank you.
