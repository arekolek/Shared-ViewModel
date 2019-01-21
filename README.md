# Shared-ViewModel

`MainFragment` hosts two fragments:

    childFragmentManager.commit {
        add(R.id.main, Fragment1.newInstance())
        add(R.id.main, Fragment2.newInstance())
    }

`Fragment1` passes clicks to shared view model:

    private val viewModel: MainViewModel by viewModels(::requireParentFragment)
    
    // ...
    
    button.setOnClickListener { viewModel.handleClick() }

`Fragment2` displays the number of times the button was clicked:

    private val viewModel: MainViewModel by viewModels(::requireParentFragment)
    
    // ...
    
    viewModel.text.observe(this) {
        textView.text = it
    }
