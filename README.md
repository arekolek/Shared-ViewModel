# Shared-ViewModel

`MainFragment` hosts two fragments:

    childFragmentManager.transaction {
        add(R.id.main, Fragment1.newInstance())
        add(R.id.main, Fragment2.newInstance())
    }

`Fragment1` passess clicks to shared view model:

    viewModel = ViewModelProvider(parentFragment!!, NewInstanceFactory()).get()
    button.setOnClickListener { viewModel.handleClick() }

`Fragment2` displays the number of times the button was clicked:

    viewModel = ViewModelProvider(parentFragment!!, NewInstanceFactory()).get()
    viewModel.text.observe(this) {
        textView.text = it
    }
