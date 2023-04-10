import React from 'react'; 
import { useEffect } from 'react';
import "../Styles/Home.css"; 
import AOS from 'aos';
import 'aos/dist/aos.css';


function Home()
{
    useEffect(() => {
        AOS.init({duration : 2000});
        document.title = 'Cerealboxd';
    }, []);

    return(
        <div>
            <div class = "BannerNButton">
                <img src={require('../Images/BannerFade.jpg')} alt='banner' className="banner"/>
                <a href="LoginPage">
                    <button class = "logBut">Login</button>
                </a>
                <a href="RegisterPage">
                    <button class = "regBut">Register</button>
                </a>
            </div>
            <h1 data-aos = "fade-up">Featured Articles</h1>
            <br></br>
            <hr></hr>
           <section data-aos = "fade-up" class="articles">
                <article>
                    <div class="article-wrapper">
                        <figure>
                            <img src={require('../Images/SpiritSplash.jpg')} className="cards" />
                        </figure>
                        <div class="article-body">
                            <h2>&nbsp;Featured Event: Spirit Splash</h2>
                            <p>
                            &nbsp;Enter Description Here.
                            </p>
                            <a href="#" class="read-more">
                            &nbsp;Read more <span class="sr-only">...</span>
                            </a>
                        </div>
                    </div>
                </article>

                <article>
                    <div class="article-wrapper">
                        <figure>
                            <img src={require('../Images/NewRSO.jpg')} className="cards" />
                        </figure>
                        <div class="article-body">
                            <h2>&nbsp;New RSOs This Week 4/2/23</h2>
                            <p>
                            &nbsp;Enter some description here.
                            </p>
                            <a href="#" class="read-more">
                            &nbsp;Read more <span class="sr-only">...</span>
                            </a>
                        </div>
                    </div>
                </article>

                <article>
                    <div class="article-wrapper">
                        <figure>
                            <img src={require('../Images/Holi.jpg')} className="cards" />
                        </figure>
                        <div class="article-body">
                            <h2>&nbsp;Holi: ISA's Take On An Indian Classic</h2>
                            <p>
                            &nbsp;Some description here!!
                            </p>
                            <a href="#" class="read-more">
                            &nbsp;Read more <span class="sr-only">...</span>
                            </a>
                        </div>
                    </div>
                </article>
            </section>
        </div>
           
        
    );

};




export default Home;

