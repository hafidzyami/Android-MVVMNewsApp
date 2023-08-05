package com.example.newsapp.ui.article

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.repository.NewsRepository
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

class ArticleFragment : Fragment(){
    private var _binding: FragmentArticleBinding? = null
    val args : ArticleFragmentArgs by navArgs()
    lateinit var articleViewModel: ArticleViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository = NewsRepository(ArticleDatabase(requireActivity().applicationContext))
        val factory = ArticleViewModelFactory(repository)
        articleViewModel = ViewModelProvider(this, factory)[ArticleViewModel::class.java]

        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.d("MYTAG", "ONCREATEVIEW")


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val article = args.article

        Log.d("MYTAG", "ONVIEWCREATE")
        binding.webView.apply {
            try{
                webViewClient = WebViewClient()
                loadUrl(article.url)
            }
            catch (e : Exception){
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
            }
        }

        binding.fab.setOnClickListener {
            articleViewModel.saveArticle(article)
            Snackbar.make(view, "Article Saved Successfully", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MYTAG", "ANCUR")
        _binding = null
    }
}