package com.learnprogrammingacademy.sampler.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.learnprogrammingacademy.sampler.common.SampleBase
import com.learnprogrammingacademy.sampler.utils.clearScreen
import com.learnprogrammingacademy.sampler.utils.logger
import com.learnprogrammingacademy.sampler.utils.toInternalFile
import com.learnprogrammingacademy.sampler.utils.use

/**
 * @author goran on 26/10/2017.
 */
class SpriteBatchSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<SpriteBatchSample>()
    }

    lateinit var camera: OrthographicCamera
    lateinit var viewport: Viewport
    lateinit var batch: SpriteBatch
    lateinit var texture: Texture

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        log.debug("create()")

        camera = OrthographicCamera()
        viewport = FitViewport(10.8f, 7.2f, camera)
        batch = SpriteBatch()
        texture = Texture("raw/character.png".toInternalFile())
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun render() {
       clearScreen()

        batch.projectionMatrix = camera.combined

        /*
        batch.begin()
        draw()
        batch.end()
        */

        batch.use { draw() }
    }

    private fun draw() {
        val width = 1f // world units
        val height = 1f // world units

        batch.draw(
                texture,
                1f, 1f,
                width / 2f, height / 2f,
                width, height,
                1f, 1f,
                0f,
                texture.width / 2, texture.height / 2,
                texture.width, texture.height,
                false, false
        )

        // scaled texture
        batch.draw(
                texture,
                4f, 2f,
                width / 2f, height / 2f,
                width, height,
                2f, 2f,
                0f,
                0, 0,
                texture.width, texture.height,
                false, false
        )

        // batch color
        val oldColor = batch.color
        batch.color = Color.GREEN

        batch.draw(
                texture,
                8f, 1f,
                width / 2f, height / 2f,
                width, height,
                2f, 2f,
                0f,
                0, 0,
                texture.width, texture.height,
                false, true
        )

        batch.color = oldColor
    }

    override fun dispose() {
        log.debug("dispose")
        batch.dispose()
        texture.dispose()
    }
}