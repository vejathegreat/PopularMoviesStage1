package com.nanodegree.velaphi.popularmovies.injection;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.nanodegree.velaphi.popularmovies.MoviesApplication;
import com.nanodegree.velaphi.popularmovies.database.FavoriteMovieDatabase;
import com.nanodegree.velaphi.popularmovies.repository.MovieDetailsRepository;
import com.nanodegree.velaphi.popularmovies.repository.MovieDetailsRepositoryImpl;
import com.nanodegree.velaphi.popularmovies.repository.PopularMoviesRepository;
import com.nanodegree.velaphi.popularmovies.repository.PopularMoviesRepositoryIml;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesModule {
    private MoviesApplication moviesApplication;

    public MoviesModule(MoviesApplication moviesApplication) {
        this.moviesApplication = moviesApplication;
    }

    @Provides
    Context applicationContext(){return moviesApplication;}

    @Provides
    @Singleton
    MovieDetailsRepository providesMovieDetailsRepository(FavoriteMovieDatabase favoriteMovieDatabase)
    {
        return new MovieDetailsRepositoryImpl(favoriteMovieDatabase);
    }


    @Provides
    @Singleton
    PopularMoviesRepository providesPopularMoviesRepository(FavoriteMovieDatabase favoriteMovieDatabase)
    {
        return new PopularMoviesRepositoryIml(favoriteMovieDatabase);
    }

    @Provides
    @Singleton
    FavoriteMovieDatabase providesFavoriteMovieDatabase(Context context)
    {
        return Room.databaseBuilder(context.getApplicationContext(), FavoriteMovieDatabase.class, "favorite_movies.db").build();
    }
}
