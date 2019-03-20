/*
* Copyright 2018 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package androidx.ui.painting

import androidx.ui.engine.text.BaselineShift
import androidx.ui.engine.text.FontStyle
import androidx.ui.engine.text.FontSynthesis
import androidx.ui.engine.text.FontWeight
import androidx.ui.engine.text.ParagraphStyle
import androidx.ui.engine.text.TextAlign
import androidx.ui.engine.text.TextBaseline
import androidx.ui.engine.text.TextDecoration
import androidx.ui.engine.text.TextDirection
import androidx.ui.engine.text.TextGeometricTransform
import androidx.ui.engine.text.font.FontFamily
import androidx.ui.engine.text.lerp
import androidx.ui.engine.window.Locale
import androidx.ui.painting.basictypes.RenderComparison
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TextStyleTest {
    @Test
    fun `constructor with default values`() {
        val textStyle = TextStyle()

        assertThat(textStyle.inherit).isTrue()
        assertThat(textStyle.color).isNull()
        assertThat(textStyle.fontSize).isNull()
        assertThat(textStyle.fontWeight).isNull()
        assertThat(textStyle.fontStyle).isNull()
        assertThat(textStyle.letterSpacing).isNull()
        assertThat(textStyle.wordSpacing).isNull()
        assertThat(textStyle.textBaseline).isNull()
        assertThat(textStyle.height).isNull()
        assertThat(textStyle.locale).isNull()
        assertThat(textStyle.background).isNull()
        assertThat(textStyle.decoration).isNull()
//        assertThat(textStyle.debugLabel).isNull()
        assertThat(textStyle.fontFamily).isNull()
    }

    @Test
    fun `constructor with customized inherit`() {
        val textStyle = TextStyle(inherit = false)

        assertThat(textStyle.inherit).isFalse()
    }

    @Test
    fun `constructor with customized color`() {
        val color = Color(0xFF00FF00.toInt())

        val textStyle = TextStyle(color = color)

        assertThat(textStyle.color).isEqualTo(color)
    }

    @Test
    fun `constructor with customized fontSize`() {
        val fontSize = 18.0f

        val textStyle = TextStyle(fontSize = fontSize)

        assertThat(textStyle.fontSize).isEqualTo(fontSize)
    }

    @Test
    fun `constructor with customized fontWeight`() {
        val fontWeight = FontWeight.w500

        val textStyle = TextStyle(fontWeight = fontWeight)

        assertThat(textStyle.fontWeight).isEqualTo(fontWeight)
    }

    @Test
    fun `constructor with customized fontStyle`() {
        val fontStyle = FontStyle.italic

        val textStyle = TextStyle(fontStyle = fontStyle)

        assertThat(textStyle.fontStyle).isEqualTo(fontStyle)
    }

    @Test
    fun `constructor with customized letterSpacing`() {
        val letterSpacing = 1.0f

        val textStyle = TextStyle(letterSpacing = letterSpacing)

        assertThat(textStyle.letterSpacing).isEqualTo(letterSpacing)
    }

    @Test
    fun `constructor with customized wordSpacing`() {
        val wordSpacing = 2.0f

        val textStyle = TextStyle(wordSpacing = wordSpacing)

        assertThat(textStyle.wordSpacing).isEqualTo(wordSpacing)
    }

    @Test
    fun `constructor with customized textBaseline`() {
        val textBaseline = TextBaseline.alphabetic

        val textStyle = TextStyle(textBaseline = textBaseline)

        assertThat(textStyle.textBaseline).isEqualTo(textBaseline)
    }

    @Test
    fun `constructor with customized baselineShift`() {
        val baselineShift = BaselineShift.SUPERSCRIPT

        val textStyle = TextStyle(baselineShift = baselineShift)

        assertThat(textStyle.baselineShift).isEqualTo(baselineShift)
    }

    @Test
    fun `constructor with customized height`() {
        val height = 123.0f

        val textStyle = TextStyle(height = height)

        assertThat(textStyle.height).isEqualTo(height)
    }

    @Test
    fun `constructor with customized locale`() {
        val locale = Locale("en", "US")

        val textStyle = TextStyle(locale = locale)

        assertThat(textStyle.locale).isEqualTo(locale)
    }

    @Test
    fun `constructor with customized background`() {
        val color = Color(0xFF00FF00.toInt())

        val textStyle = TextStyle(background = color)

        assertThat(textStyle.background).isEqualTo(color)
    }

    @Test
    fun `constructor with customized decoration`() {
        val decoration = TextDecoration.underline

        val textStyle = TextStyle(decoration = decoration)

        assertThat(textStyle.decoration).isEqualTo(decoration)
    }

    /*@Test
    fun `constructor with customized debugLabel`() {
        val label = "foo"

        val textStyle = TextStyle(debugLabel = label)

        assertThat(textStyle.debugLabel).isEqualTo(label)
    }*/

    @Test
    fun `constructor with customized fontFamily`() {
        val fontFamily = FontFamily(genericFamily = "sans-serif")

        val textStyle = TextStyle(fontFamily = fontFamily)

        assertThat(textStyle.fontFamily).isEqualTo(fontFamily)
    }

    @Test
    fun `apply with empty parameter list`() {
        val textStyle = TextStyle()

        textStyle.apply()

        assertThat(textStyle.inherit).isTrue()
        assertThat(textStyle.color).isNull()
        assertThat(textStyle.fontSize).isNull()
        assertThat(textStyle.fontWeight).isNull()
        assertThat(textStyle.fontStyle).isNull()
        assertThat(textStyle.letterSpacing).isNull()
        assertThat(textStyle.wordSpacing).isNull()
        assertThat(textStyle.textBaseline).isNull()
        assertThat(textStyle.baselineShift).isNull()
        assertThat(textStyle.height).isNull()
        assertThat(textStyle.locale).isNull()
        assertThat(textStyle.background).isNull()
        assertThat(textStyle.decoration).isNull()
        assertThat(textStyle.debugLabel).isNull()
        assertThat(textStyle.fontFamily).isNull()
        assertThat(textStyle.fontSynthesis).isNull()
    }

    @Test
    fun `apply with color`() {
        val textStyle = TextStyle()
        val color = Color(0xFF00FF00.toInt())

        val newTextStyle = textStyle.apply(color = color)

        assertThat(newTextStyle.color).isEqualTo(color)
    }

    @Test
    fun `apply with decoration`() {
        val textStyle = TextStyle()
        val decoration = TextDecoration.lineThrough

        val newTextStyle = textStyle.apply(decoration = decoration)

        assertThat(newTextStyle.decoration).isEqualTo(decoration)
    }

    @Test
    fun `apply with fontFamily`() {
        val textStyle = TextStyle()
        val fontFamily = FontFamily(genericFamily = "sans-serif")

        val newTextStyle = textStyle.apply(fontFamily = fontFamily)

        assertThat(newTextStyle.fontFamily).isEqualTo(fontFamily)
    }

    @Test
    fun `apply with fontSizeFactor and fontSizeDelta`() {
        val textStyle = TextStyle(fontSize = 15.0f)

        val newTextStyle = textStyle.apply(fontSizeFactor = 2.3f, fontSizeDelta = -2.0f)

        // fontSize * fontSizeFactor + fontSizeDelta = 15.0f * 2.3f + (-2.0f) = 32.5f
        assertThat(newTextStyle.fontSize).isWithin(0.00001f).of(32.5f)
    }

    @Test
    fun `apply with fontWeightDelta`() {
        val textStyle = TextStyle(fontWeight = FontWeight.w700)

        val newTextStyle = textStyle.apply(fontWeightDelta = -2)

        // FontWeight.values[(fontWeight.index + fontWeightDelta)
        // .clamp(0, FontWeight.values.size - 1)] =
        // FontWeight.values[(8 + (-2)).clamp(0, 9 - 1)] = FontWeight.values[6] = FontWeight.w500
        assertThat(newTextStyle.fontWeight).isEqualTo(FontWeight.w500)
    }

    @Test
    fun `apply with fontWeightDelta out of range`() {
        val textStyle = TextStyle(fontWeight = FontWeight.w300)

        val newTextStyle = textStyle.apply(fontWeightDelta = -5)

        assertThat(newTextStyle.fontWeight).isEqualTo(FontWeight.w100)
    }

    @Test
    fun `apply with letterSpacingFactor and letterSpacingDelta`() {
        val textStyle = TextStyle(letterSpacing = 2.2f)

        val newTextStyle = textStyle.apply(letterSpacingFactor = 2.8f, letterSpacingDelta = 1.3f)

        // letterSpacing * letterSpacingFactor + letterSpacingDelta = 2.2f * 2.8f + 1.3f = 7.46f
        assertThat(newTextStyle.letterSpacing).isEqualTo(7.46f)
    }

    @Test
    fun `apply with wordSpacingFactor and wordSpacingDelta`() {
        val textStyle = TextStyle(wordSpacing = 3.1f)

        val newTextStyle = textStyle.apply(wordSpacingFactor = 1.3f, wordSpacingDelta = -0.22f)

        // wordSpacing * wordSpacingFactor + wordSpacingDelta = 3.1 * 1.3 + (-0.22) = 3.81
        assertThat(newTextStyle.wordSpacing).isWithin(0.00001f).of(3.81f)
    }

    @Test
    fun `apply with heightFactor and heightDelta`() {
        val textStyle = TextStyle(height = 124.0f)

        val newTextStyle = textStyle.apply(heightFactor = 1.3f, heightDelta = 3.7f)

        // height * heightFactor + heightDelta = 124.0 * 1.3 + 3.7 = 164.9
        assertThat(newTextStyle.height).isWithin(0.00001f).of(164.9f)
    }

    @Test
    fun `apply changes debugLabel in return value`() {
        val textStyle = TextStyle(debugLabel = "foo")

        val newTextStyle = textStyle.apply()

        assertThat(newTextStyle.debugLabel).isEqualTo("(foo).apply")
    }

    @Test
    fun `merge with empty other should return this`() {
        val textStyle = TextStyle()

        val newTextStyle = textStyle.merge()

        assertThat(newTextStyle).isEqualTo(textStyle)
    }

    @Test
    fun `merge with other's inherit is false should return other`() {
        val textStyle = TextStyle()
        val otherTextStyle = TextStyle(inherit = false)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle).isEqualTo(otherTextStyle)
    }

    @Test
    fun `merge with other's color is null should use this' color`() {
        val color = Color(0xFF00FF00.toInt())
        val textStyle = TextStyle(color = color)
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.color).isEqualTo(color)
    }

    @Test
    fun `merge with other's color is set should use other's color`() {
        val color = Color(0xFF00FF00.toInt())
        val otherColor = Color(0x00FFFF00)
        val textStyle = TextStyle(color = color)
        val otherTextStyle = TextStyle(color = otherColor)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.color).isEqualTo(otherColor)
    }

    @Test
    fun `merge with other's fontFamily is null should use this' fontFamily`() {
        val fontFamily = FontFamily(genericFamily = "sans-serif")
        val textStyle = TextStyle(fontFamily = fontFamily)
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.fontFamily).isEqualTo(fontFamily)
    }

    @Test
    fun `merge with other's fontFamily is set should use other's fontFamily`() {
        val fontFamily = FontFamily(genericFamily = "sans-serif")
        val otherFontFamily = FontFamily(genericFamily = "serif")
        val textStyle = TextStyle(fontFamily = fontFamily)
        val otherTextStyle = TextStyle(fontFamily = otherFontFamily)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.fontFamily).isEqualTo(otherFontFamily)
    }

    @Test
    fun `merge with other's fontSize is null should use this' fontSize`() {
        val fontSize = 3.5f
        val textStyle = TextStyle(fontSize = fontSize)
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.fontSize).isEqualTo(fontSize)
    }

    @Test
    fun `merge with other's fontSize is set should use other's fontSize`() {
        val fontSize = 3.5f
        val otherFontSize = 8.7f
        val textStyle = TextStyle(fontSize = fontSize)
        val otherTextStyle = TextStyle(fontSize = otherFontSize)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.fontSize).isEqualTo(otherFontSize)
    }

    @Test
    fun `merge with other's fontWeight is null should use this' fontWeight`() {
        val fontWeight = FontWeight.w300
        val textStyle = TextStyle(fontWeight = fontWeight)
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.fontWeight).isEqualTo(fontWeight)
    }

    @Test
    fun `merge with other's fontWeight is set should use other's fontWeight`() {
        val fontWeight = FontWeight.w300
        val otherFontWeight = FontWeight.w500
        val textStyle = TextStyle(fontWeight = fontWeight)
        val otherTextStyle = TextStyle(fontWeight = otherFontWeight)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.fontWeight).isEqualTo(otherFontWeight)
    }

    @Test
    fun `merge with other's fontStyle is null should use this' fontStyle`() {
        val fontStyle = FontStyle.italic
        val textStyle = TextStyle(fontStyle = fontStyle)
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.fontStyle).isEqualTo(fontStyle)
    }

    @Test
    fun `merge with other's fontStyle is set should use other's fontStyle`() {
        val fontStyle = FontStyle.italic
        val otherFontStyle = FontStyle.normal
        val textStyle = TextStyle(fontStyle = fontStyle)
        val otherTextStyle = TextStyle(fontStyle = otherFontStyle)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.fontStyle).isEqualTo(otherFontStyle)
    }

    @Test
    fun `merge with other's fontSynthesis is null should use this' fontSynthesis`() {
        val fontSynthesis = FontSynthesis.style
        val textStyle = TextStyle(fontSynthesis = fontSynthesis)
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.fontSynthesis).isEqualTo(fontSynthesis)
    }

    @Test
    fun `merge with other's fontSynthesis is set should use other's fontSynthesis`() {
        val fontSynthesis = FontSynthesis.style
        val otherFontSynthesis = FontSynthesis.weight

        val textStyle = TextStyle(fontSynthesis = fontSynthesis)
        val otherTextStyle = TextStyle(fontSynthesis = otherFontSynthesis)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.fontSynthesis).isEqualTo(otherFontSynthesis)
    }

    @Test
    fun `merge with other's fontFeature is null should use this' fontSynthesis`() {
        val fontFeatureSettings = "\"kern\" 0"
        val textStyle = TextStyle(fontFeatureSettings = fontFeatureSettings)
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.fontFeatureSettings).isEqualTo(fontFeatureSettings)
    }

    @Test
    fun `merge with other's fontFeature is set should use other's fontSynthesis`() {
        val fontFeatureSettings = "\"kern\" 0"
        val otherFontFeatureSettings = "\"kern\" 1"

        val textStyle = TextStyle(fontFeatureSettings = fontFeatureSettings)
        val otherTextStyle = TextStyle(fontFeatureSettings = otherFontFeatureSettings)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.fontFeatureSettings).isEqualTo(otherFontFeatureSettings)
    }

    @Test
    fun `merge with other's letterSpacing is null should use this' letterSpacing`() {
        val letterSpacing = 1.2f
        val textStyle = TextStyle(letterSpacing = letterSpacing)
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.letterSpacing).isEqualTo(letterSpacing)
    }

    @Test
    fun `merge with other's letterSpacing is set should use other's letterSpacing`() {
        val letterSpacing = 1.2f
        val otherLetterSpacing = 1.5f
        val textStyle = TextStyle(letterSpacing = letterSpacing)
        val otherTextStyle = TextStyle(letterSpacing = otherLetterSpacing)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.letterSpacing).isEqualTo(otherLetterSpacing)
    }

    @Test
    fun `merge with other's wordSpacing is null should use this' wordSpacing`() {
        val wordSpacing = 1.2f
        val textStyle = TextStyle(wordSpacing = wordSpacing)
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.wordSpacing).isEqualTo(wordSpacing)
    }

    @Test
    fun `merge with other's wordSpacing is set should use other's wordSpacing`() {
        val wordSpacing = 1.2f
        val otherWordSpacing = 1.5f
        val textStyle = TextStyle(wordSpacing = wordSpacing)
        val otherTextStyle = TextStyle(wordSpacing = otherWordSpacing)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.wordSpacing).isEqualTo(otherWordSpacing)
    }

    @Test
    fun `merge with other's textBaseline is null should use this' textBaseline`() {
        val textBaseline = TextBaseline.alphabetic
        val textStyle = TextStyle(textBaseline = textBaseline)
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.textBaseline).isEqualTo(textBaseline)
    }

    @Test
    fun `merge with other's textBaseline is set should use other's textBaseline`() {
        val textBaseline = TextBaseline.alphabetic
        val otherTextBaseline = TextBaseline.ideographic
        val textStyle = TextStyle(textBaseline = textBaseline)
        val otherTextStyle = TextStyle(textBaseline = otherTextBaseline)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.textBaseline).isEqualTo(otherTextBaseline)
    }

    @Test
    fun `merge with other's baselineShift is null should use this' baselineShift`() {
        val baselineShift = BaselineShift.SUPERSCRIPT
        val textStyle = TextStyle(baselineShift = baselineShift)
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.baselineShift).isEqualTo(baselineShift)
    }

    @Test
    fun `merge with other's baselineShift is set should use other's baselineShift`() {
        val baselineShift = BaselineShift.SUPERSCRIPT
        val otherBaselineShift = BaselineShift.SUBSCRIPT
        val textStyle = TextStyle(baselineShift = baselineShift)
        val otherTextStyle = TextStyle(baselineShift = otherBaselineShift)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.baselineShift).isEqualTo(otherBaselineShift)
    }

    @Test
    fun `merge with other's height is null should use this' height`() {
        val height = 123.0f
        val textStyle = TextStyle(height = height)
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.height).isEqualTo(height)
    }

    @Test
    fun `merge with other's height is set should use other's height`() {
        val height = 123.0f
        val otherHeight = 200.0f
        val textStyle = TextStyle(height = height)
        val otherTextStyle = TextStyle(height = otherHeight)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.height).isEqualTo(otherHeight)
    }

    @Test
    fun `merge with other's background is null should use this' background`() {
        val color = Color(0xFF00FF00.toInt())
        val textStyle = TextStyle(background = color)
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.background).isEqualTo(color)
    }

    @Test
    fun `merge with other's background is set should use other's background`() {
        val color = Color(0xFF00FF00.toInt())
        val otherColor = Color(0xFF0000FF.toInt())
        val textStyle = TextStyle(background = color)
        val otherTextStyle = TextStyle(background = otherColor)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.background).isEqualTo(otherColor)
    }

    @Test
    fun `merge with other's decoration is null should use this' decoration`() {
        val decoration = TextDecoration.lineThrough
        val textStyle = TextStyle(decoration = decoration)
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.decoration).isEqualTo(decoration)
    }

    @Test
    fun `merge with other's decoration is set should use other's decoration`() {
        val decoration = TextDecoration.lineThrough
        val otherDecoration = TextDecoration.underline
        val textStyle = TextStyle(decoration = decoration)
        val otherTextStyle = TextStyle(decoration = otherDecoration)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.decoration).isEqualTo(otherDecoration)
    }

    @Test
    fun `merge with other's locale is null should use this' locale`() {
        val locale = Locale("en", "US")
        val textStyle = TextStyle(locale = locale)
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.locale).isEqualTo(locale)
    }

    @Test
    fun `merge with other's locale is set should use other's locale`() {
        val locale = Locale("en", "US")
        val otherLocale = Locale("ja", "JP")
        val textStyle = TextStyle(locale = locale)
        val otherTextStyle = TextStyle(locale = otherLocale)

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.locale).isEqualTo(otherLocale)
    }

    @Test
    fun `merge without debugLabel result's debugLabel should be empty`() {
        val textStyle = TextStyle()
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.debugLabel).isEmpty()
    }

    @Test
    fun `merge with customized this's debugLabel only`() {
        val textStyle = TextStyle(debugLabel = "foo")
        val otherTextStyle = TextStyle()

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.debugLabel).isEqualTo("(foo).merge(unknown)")
    }

    @Test
    fun `merge with customized other's debugLabel only`() {
        val textStyle = TextStyle()
        val otherTextStyle = TextStyle(debugLabel = "bar")

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.debugLabel).isEqualTo("(unknown).merge(bar)")
    }

    @Test
    fun `merge with customized this' and other's debugLabel`() {
        val textStyle = TextStyle(debugLabel = "foo")
        val otherTextStyle = TextStyle(debugLabel = "bar")

        val newTextStyle = textStyle.merge(otherTextStyle)

        assertThat(newTextStyle.debugLabel).isEqualTo("(foo).merge(bar)")
    }

    @Test
    fun `merge with chained debugLabel`() {
        val bar = TextStyle(debugLabel = "bar", fontSize = 2.0f)
        val baz = TextStyle(debugLabel = "baz", fontSize = 3.0f)
        val fo = TextStyle(debugLabel = "foo", fontSize = 1.0f)

        assertThat(fo.merge(bar).merge(baz).debugLabel).isEqualTo("((foo).merge(bar)).merge(baz)")
    }

    @Test
    fun `lerp with both Null Textstyles`() {
        val newTextStyle = TextStyle.lerp(t = 1.0f)

        assertThat(newTextStyle).isEqualTo(null)
    }

    @Test
    fun `lerp color with a is Null and t is smaller than half`() {
        val color = Color(0xFF00FF00.toInt())
        val t = 0.3f
        val textStyle = TextStyle(color = color)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.color).isEqualTo(Color.lerp(a = null, b = color, t = t))
    }

    @Test
    fun `lerp color with a is Null and t is larger than half`() {
        val color = Color(0xFF00FF00.toInt())
        val t = 0.8f
        val textStyle = TextStyle(color = color)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.color).isEqualTo(Color.lerp(a = null, b = color, t = t))
    }

    @Test
    fun `lerp color with b is Null and t is smaller than half`() {
        val color = Color(0xFF00FF00.toInt())
        val t = 0.3f
        val textStyle = TextStyle(color = color)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.color).isEqualTo(Color.lerp(a = color, b = null, t = t))
    }

    @Test
    fun `lerp color with b is Null and t is larger than half`() {
        val color = Color(0xFF00FF00.toInt())
        val t = 0.8f
        val textStyle = TextStyle(color = color)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.color).isEqualTo(Color.lerp(a = color, b = null, t = t))
    }

    @Test
    fun `lerp color with a and b are not Null`() {
        val color1 = Color(0xFF00FF00.toInt())
        val color2 = Color(0x00FFFF00)
        val t = 0.3f
        val textStyle1 = TextStyle(
            color = color1,
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f
        )
        val textStyle2 = TextStyle(
            color = color2,
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.color).isEqualTo(Color.lerp(a = color1, b = color2, t = t))
    }

    @Test
    fun `lerp fontFamily with a is Null and t is smaller than half`() {
        val fontFamily = FontFamily(genericFamily = "sans-serif")
        val t = 0.3f
        val textStyle = TextStyle(fontFamily = fontFamily)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.fontFamily).isNull()
    }

    @Test
    fun `lerp fontFamily with a is Null and t is larger than half`() {
        val fontFamily = FontFamily(genericFamily = "sans-serif")
        val t = 0.7f
        val textStyle = TextStyle(fontFamily = fontFamily)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.fontFamily).isEqualTo(fontFamily)
    }

    @Test
    fun `lerp fontFamily with b is Null and t is smaller than half`() {
        val fontFamily = FontFamily(genericFamily = "sans-serif")
        val t = 0.3f
        val textStyle = TextStyle(fontFamily = fontFamily)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.fontFamily).isEqualTo(fontFamily)
    }

    @Test
    fun `lerp fontFamily with b is Null and t is larger than half`() {
        val fontFamily = FontFamily(genericFamily = "sans-serif")
        val t = 0.7f
        val textStyle = TextStyle(fontFamily = fontFamily)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.fontFamily).isNull()
    }

    @Test
    fun `lerp fontFamily with a and b are not Null and t is smaller than half`() {
        val fontFamily1 = FontFamily(genericFamily = "sans-serif")
        val fontFamily2 = FontFamily(genericFamily = "serif")
        val t = 0.3f
        val textStyle1 = TextStyle(
            fontFamily = fontFamily1,
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f
        )
        val textStyle2 = TextStyle(
            fontFamily = fontFamily2,
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.fontFamily).isEqualTo(fontFamily1)
    }

    @Test
    fun `lerp fontFamily with a and b are not Null and t is larger than half`() {
        val fontFamily1 = FontFamily(genericFamily = "sans-serif")
        val fontFamily2 = FontFamily(genericFamily = "serif")
        val t = 0.8f
        val textStyle1 = TextStyle(
            fontFamily = fontFamily1,
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f
        )
        val textStyle2 = TextStyle(
            fontFamily = fontFamily2,
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.fontFamily).isEqualTo(fontFamily2)
    }

    @Test
    fun `lerp fontSize with a is Null and t is smaller than half`() {
        val fontSize = 8.0f
        val t = 0.3f
        val textStyle = TextStyle(fontSize = fontSize)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.fontSize).isNull()
    }

    @Test
    fun `lerp fontSize with a is Null and t is larger than half`() {
        val fontSize = 8.0f
        val t = 0.8f
        val textStyle = TextStyle(fontSize = fontSize)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.fontSize).isEqualTo(fontSize)
    }

    @Test
    fun `lerp fontSize with b is Null and t is smaller than half`() {
        val fontSize = 8.0f
        val t = 0.3f
        val textStyle = TextStyle(fontSize = fontSize)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.fontSize).isEqualTo(fontSize)
    }

    @Test
    fun `lerp fontSize with b is Null and t is larger than half`() {
        val fontSize = 8.0f
        val t = 0.8f
        val textStyle = TextStyle(fontSize = fontSize)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.fontSize).isNull()
    }

    @Test
    fun `lerp fontSize with a and b are not Null`() {
        val fontSize1 = 8.0f
        val fontSize2 = 16.0f
        val t = 0.8f
        val textStyle1 = TextStyle(
            fontSize = fontSize1,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f
        )
        val textStyle2 = TextStyle(
            fontSize = fontSize2,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        // a + (b - a) * t = 8.0f + (16.0f  - 8.0f) * 0.8f = 14.4f
        assertThat(newTextStyle?.fontSize).isEqualTo(14.4f)
    }

    @Test
    fun `lerp fontWeight with a is Null and t is smaller than half`() {
        val fontWeight = FontWeight.w700
        val t = 0.3f
        val textStyle = TextStyle(fontWeight = fontWeight)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.fontWeight).isEqualTo(FontWeight.lerp(null, fontWeight, t))
    }

    @Test
    fun `lerp fontWeight with a is Null and t is larger than half`() {
        val fontWeight = FontWeight.w700
        val t = 0.8f
        val textStyle = TextStyle(fontWeight = fontWeight)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.fontWeight).isEqualTo(FontWeight.lerp(null, fontWeight, t))
    }

    @Test
    fun `lerp fontWeight with b is Null and t is smaller than half`() {
        val fontWeight = FontWeight.w700
        val t = 0.3f
        val textStyle = TextStyle(fontWeight = fontWeight)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.fontWeight).isEqualTo(FontWeight.lerp(fontWeight, null, t))
    }

    @Test
    fun `lerp fontWeight with b is Null and t is larger than half`() {
        val fontWeight = FontWeight.w700
        val t = 0.8f
        val textStyle = TextStyle(fontWeight = fontWeight)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.fontWeight).isEqualTo(FontWeight.lerp(fontWeight, null, t))
    }

    @Test
    fun `lerp fontWeight with a and b are not Null`() {
        val fontWeight1 = FontWeight.w200
        val fontWeight2 = FontWeight.w500
        val t = 0.8f
        val textStyle1 = TextStyle(
            fontWeight = fontWeight1,
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f
        )
        val textStyle2 = TextStyle(
            fontWeight = fontWeight2,
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.fontWeight).isEqualTo(FontWeight.lerp(fontWeight1, fontWeight2, t))
    }

    @Test
    fun `lerp fontStyle with a is Null and t is smaller than half`() {
        val fontStyle = FontStyle.italic
        val t = 0.3f
        val textStyle = TextStyle(fontStyle = fontStyle)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.fontStyle).isNull()
    }

    @Test
    fun `lerp fontStyle with a is Null and t is larger than half`() {
        val fontStyle = FontStyle.italic
        val t = 0.8f
        val textStyle = TextStyle(fontStyle = fontStyle)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.fontStyle).isEqualTo(fontStyle)
    }

    @Test
    fun `lerp fontStyle with b is Null and t is smaller than half`() {
        val fontStyle = FontStyle.italic
        val t = 0.3f
        val textStyle = TextStyle(fontStyle = fontStyle)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.fontStyle).isEqualTo(fontStyle)
    }

    @Test
    fun `lerp fontStyle with b is Null and t is larger than half`() {
        val fontStyle = FontStyle.italic
        val t = 0.8f
        val textStyle = TextStyle(fontStyle = fontStyle)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.fontStyle).isNull()
    }

    @Test
    fun `lerp fontStyle with a and b are not Null and t is smaller than half`() {
        val fontStyle1 = FontStyle.italic
        val fontStyle2 = FontStyle.normal
        // attributes other than fontStyle are required for lerp not to throw an exception
        val t = 0.3f
        val textStyle1 = TextStyle(
            fontStyle = fontStyle1,
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f
        )
        val textStyle2 = TextStyle(
            fontStyle = fontStyle2,
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.fontStyle).isEqualTo(fontStyle1)
    }

    @Test
    fun `lerp fontStyle with a and b are not Null and t is larger than half`() {
        val fontStyle1 = FontStyle.italic
        val fontStyle2 = FontStyle.normal
        // attributes other than fontStyle are required for lerp not to throw an exception
        val t = 0.8f
        val textStyle1 = TextStyle(
            fontStyle = fontStyle1,
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f
        )
        val textStyle2 = TextStyle(
            fontStyle = fontStyle2,
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.fontStyle).isEqualTo(fontStyle2)
    }

    @Test
    fun `lerp fontSynthesis with a is Null and t is smaller than half`() {
        val fontSynthesis = FontSynthesis.style
        val t = 0.3f
        val textStyle = TextStyle(fontSynthesis = fontSynthesis)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.fontSynthesis).isNull()
    }

    @Test
    fun `lerp fontSynthesis with a is Null and t is larger than half`() {
        val fontSynthesis = FontSynthesis.style
        val t = 0.8f
        val textStyle = TextStyle(fontSynthesis = fontSynthesis)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.fontSynthesis).isEqualTo(fontSynthesis)
    }

    @Test
    fun `lerp fontSynthesis with b is Null and t is smaller than half`() {
        val fontSynthesis = FontSynthesis.style
        val t = 0.3f
        val textStyle = TextStyle(fontSynthesis = fontSynthesis)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.fontSynthesis).isEqualTo(fontSynthesis)
    }

    @Test
    fun `lerp fontSynthesis with b is Null and t is larger than half`() {
        val fontSynthesis = FontSynthesis.style
        val t = 0.8f
        val textStyle = TextStyle(fontSynthesis = fontSynthesis)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.fontSynthesis).isNull()
    }

    @Test
    fun `lerp fontSynthesis with a and b are not Null and t is smaller than half`() {
        val fontSynthesis1 = FontSynthesis.style
        val fontSynthesis2 = FontSynthesis.weight

        val t = 0.3f
        // attributes other than fontSynthesis are required for lerp not to throw an exception
        val textStyle1 = TextStyle(
            fontSynthesis = fontSynthesis1,
            fontSize = 1.0f,
            wordSpacing = 1.0f,
            letterSpacing = 1.0f,
            height = 1.0f
        )
        val textStyle2 = TextStyle(
            fontSynthesis = fontSynthesis2,
            fontSize = 1.0f,
            wordSpacing = 1.0f,
            letterSpacing = 1.0f,
            height = 1.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.fontSynthesis).isEqualTo(fontSynthesis1)
    }

    @Test
    fun `lerp fontSynthesis with a and b are not Null and t is larger than half`() {
        val fontSynthesis1 = FontSynthesis.style
        val fontSynthesis2 = FontSynthesis.weight

        val t = 0.8f
        // attributes other than fontSynthesis are required for lerp not to throw an exception
        val textStyle1 = TextStyle(
            fontSynthesis = fontSynthesis1,
            fontSize = 1.0f,
            wordSpacing = 1.0f,
            letterSpacing = 1.0f,
            height = 1.0f
        )
        val textStyle2 = TextStyle(
            fontSynthesis = fontSynthesis2,
            fontSize = 1.0f,
            wordSpacing = 1.0f,
            letterSpacing = 1.0f,
            height = 1.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.fontSynthesis).isEqualTo(fontSynthesis2)
    }

    @Test
    fun `lerp fontFeatureSettings with a is Null and t is smaller than half`() {
        val fontFeatureSettings = "\"kern\" 0"
        val t = 0.3f
        val textStyle = TextStyle(fontFeatureSettings = fontFeatureSettings)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.fontFeatureSettings).isNull()
    }

    @Test
    fun `lerp fontFeatureSettings with a is Null and t is larger than half`() {
        val fontFeatureSettings = "\"kern\" 0"
        val t = 0.8f
        val textStyle = TextStyle(fontFeatureSettings = fontFeatureSettings)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.fontFeatureSettings).isEqualTo(fontFeatureSettings)
    }

    @Test
    fun `lerp fontFeatureSettings with b is Null and t is smaller than half`() {
        val fontFeatureSettings = "\"kern\" 0"
        val t = 0.3f
        val textStyle = TextStyle(fontFeatureSettings = fontFeatureSettings)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.fontFeatureSettings).isEqualTo(fontFeatureSettings)
    }

    @Test
    fun `lerp fontFeatureSettings with b is Null and t is larger than half`() {
        val fontFeatureSettings = "\"kern\" 0"
        val t = 0.8f
        val textStyle = TextStyle(fontFeatureSettings = fontFeatureSettings)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.fontFeatureSettings).isNull()
    }

    @Test
    fun `lerp fontFeatureSettings with a and b are not Null and t is smaller than half`() {
        val fontFeatureSettings1 = "\"kern\" 0"
        val fontFeatureSettings2 = "\"kern\" 1"

        val t = 0.3f
        // attributes other than fontSynthesis are required for lerp not to throw an exception
        val textStyle1 = TextStyle(
            fontFeatureSettings = fontFeatureSettings1,
            fontSize = 1.0f,
            wordSpacing = 1.0f,
            letterSpacing = 1.0f,
            height = 1.0f
        )
        val textStyle2 = TextStyle(
            fontFeatureSettings = fontFeatureSettings2,
            fontSize = 1.0f,
            wordSpacing = 1.0f,
            letterSpacing = 1.0f,
            height = 1.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.fontFeatureSettings).isEqualTo(fontFeatureSettings1)
    }

    @Test
    fun `lerp fontFeatureSettings with a and b are not Null and t is larger than half`() {
        val fontFeatureSettings1 = "\"kern\" 0"
        val fontFeatureSettings2 = "\"kern\" 1"

        val t = 0.8f
        // attributes other than fontSynthesis are required for lerp not to throw an exception
        val textStyle1 = TextStyle(
            fontFeatureSettings = fontFeatureSettings1,
            fontSize = 1.0f,
            wordSpacing = 1.0f,
            letterSpacing = 1.0f,
            height = 1.0f
        )
        val textStyle2 = TextStyle(
            fontFeatureSettings = fontFeatureSettings2,
            fontSize = 1.0f,
            wordSpacing = 1.0f,
            letterSpacing = 1.0f,
            height = 1.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.fontFeatureSettings).isEqualTo(fontFeatureSettings2)
    }

    @Test
    fun `lerp letterSpacing with a is Null and t is smaller than half`() {
        val letterSpacing = 2.0f
        val t = 0.3f
        val textStyle = TextStyle(letterSpacing = letterSpacing)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.letterSpacing).isNull()
    }

    @Test
    fun `lerp letterSpacing with a is Null and t is larger than half`() {
        val letterSpacing = 2.0f
        val t = 0.8f
        val textStyle = TextStyle(letterSpacing = letterSpacing)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.letterSpacing).isEqualTo(letterSpacing)
    }

    @Test
    fun `lerp letterSpacing with b is Null and t is smaller than half`() {
        val letterSpacing = 2.0f
        val t = 0.3f
        val textStyle = TextStyle(letterSpacing = letterSpacing)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.letterSpacing).isEqualTo(letterSpacing)
    }

    @Test
    fun `lerp letterSpacing with b is Null and t is larger than half`() {
        val letterSpacing = 2.0f
        val t = 0.8f
        val textStyle = TextStyle(letterSpacing = letterSpacing)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.letterSpacing).isNull()
    }

    @Test
    fun `lerp letterSpacing with a and b are not Null`() {
        val letterSpacing1 = 1.0f
        val letterSpacing2 = 3.0f
        val t = 0.8f
        val textStyle1 = TextStyle(
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = letterSpacing1,
            height = 123.0f
        )
        val textStyle2 = TextStyle(
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = letterSpacing2,
            height = 20.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        // a + (b - a) * t = 1.0f + (3.0f - 1.0f) * 0.8f = 2.6f
        assertThat(newTextStyle?.letterSpacing).isEqualTo(2.6f)
    }

    @Test
    fun `lerp wordSpacing with a is Null and t is smaller than half`() {
        val wordSpacing = 2.0f
        val t = 0.3f
        val textStyle = TextStyle(wordSpacing = wordSpacing)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.wordSpacing).isNull()
    }

    @Test
    fun `lerp wordSpacing with a is Null and t is larger than half`() {
        val wordSpacing = 2.0f
        val t = 0.7f
        val textStyle = TextStyle(wordSpacing = wordSpacing)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.wordSpacing).isEqualTo(wordSpacing)
    }

    @Test
    fun `lerp wordSpacing with b is Null and t is smaller than half`() {
        val wordSpacing = 2.0f
        val t = 0.3f
        val textStyle = TextStyle(wordSpacing = wordSpacing)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.wordSpacing).isEqualTo(wordSpacing)
    }

    @Test
    fun `lerp wordSpacing with b is Null and t is larger than half`() {
        val wordSpacing = 2.0f
        val t = 0.7f
        val textStyle = TextStyle(wordSpacing = wordSpacing)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.wordSpacing).isNull()
    }

    @Test
    fun `lerp wordSpacing with a and b are not Null`() {
        val wordSpacing1 = 1.0f
        val wordSpacing2 = 3.0f
        val t = 0.8f
        val textStyle1 = TextStyle(
            fontSize = 4.0f,
            wordSpacing = wordSpacing1,
            letterSpacing = 2.2f,
            height = 123.0f
        )
        val textStyle2 = TextStyle(
            fontSize = 7.0f,
            wordSpacing = wordSpacing2,
            letterSpacing = 3.0f,
            height = 20.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        // a + (b - a) * t = 1.0f + (3.0f - 1.0f) * 0.8f = 2.6f
        assertThat(newTextStyle?.wordSpacing).isEqualTo(2.6f)
    }

    @Test
    fun `lerp textBaseline with a is Null and t is smaller than half`() {
        val textBaseline = TextBaseline.ideographic
        val t = 0.3f
        val textStyle = TextStyle(textBaseline = textBaseline)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.textBaseline).isNull()
    }

    @Test
    fun `lerp textBaseline with a is Null and t is larger than half`() {
        val textBaseline = TextBaseline.ideographic
        val t = 0.7f
        val textStyle = TextStyle(textBaseline = textBaseline)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.textBaseline).isEqualTo(textBaseline)
    }

    @Test
    fun `lerp textBaseline with b is Null and t is smaller than half`() {
        val textBaseline = TextBaseline.ideographic
        val t = 0.3f
        val textStyle = TextStyle(textBaseline = textBaseline)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.textBaseline).isEqualTo(textBaseline)
    }

    @Test
    fun `lerp textBaseline with b is Null and t is larger than half`() {
        val textBaseline = TextBaseline.ideographic
        val t = 0.7f
        val textStyle = TextStyle(textBaseline = textBaseline)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.textBaseline).isNull()
    }

    @Test
    fun `lerp textBaseline with a and b are not Null and t is smaller than half`() {
        val textBaseline1 = TextBaseline.ideographic
        val textBaseline2 = TextBaseline.alphabetic
        val t = 0.3f
        val textStyle1 = TextStyle(
            textBaseline = textBaseline1,
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f
        )
        val textStyle2 = TextStyle(
            textBaseline = textBaseline2,
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.textBaseline).isEqualTo(textBaseline1)
    }

    @Test
    fun `lerp textBaseline with a and b are not Null and t is larger than half`() {
        val textBaseline1 = TextBaseline.ideographic
        val textBaseline2 = TextBaseline.alphabetic
        val t = 0.8f
        val textStyle1 = TextStyle(
            textBaseline = textBaseline1,
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f
        )
        val textStyle2 = TextStyle(
            textBaseline = textBaseline2,
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.textBaseline).isEqualTo(textBaseline2)
    }

    @Test
    fun `lerp baselineShift with a is Null and t is smaller than half`() {
        val baselineShift = BaselineShift.SUPERSCRIPT
        val t = 0.3f
        val textStyle = TextStyle(baselineShift = baselineShift)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.baselineShift).isNull()
    }

    @Test
    fun `lerp baselineShift with a is Null and t is larger than half`() {
        val baselineShift = BaselineShift.SUPERSCRIPT
        val t = 0.7f
        val textStyle = TextStyle(baselineShift = baselineShift)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.baselineShift).isEqualTo(baselineShift)
    }

    @Test
    fun `lerp baselineShift with b is Null and t is smaller than half`() {
        val baselineShift = BaselineShift.SUPERSCRIPT
        val t = 0.3f
        val textStyle = TextStyle(baselineShift = baselineShift)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.baselineShift).isEqualTo(baselineShift)
    }

    @Test
    fun `lerp baselineShift with b is Null and t is larger than half`() {
        val baselineShift = BaselineShift.SUPERSCRIPT
        val t = 0.7f
        val textStyle = TextStyle(baselineShift = baselineShift)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.baselineShift).isNull()
    }

    @Test
    fun `lerp baselineShift with a and b are not Null`() {
        val baselineShift1 = BaselineShift(1.0f)
        val baselineShift2 = BaselineShift(2.0f)
        val t = 0.3f
        val textStyle1 = TextStyle(
            baselineShift = baselineShift1,
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f
        )
        val textStyle2 = TextStyle(
            baselineShift = baselineShift2,
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.baselineShift)
            .isEqualTo(BaselineShift.lerp(baselineShift1, baselineShift2, t))
    }

    @Test
    fun `lerp textGeometricTransform with a is Null and t is smaller than half`() {
        val textTransform = TextGeometricTransform(scaleX = 1.5f)
        val t = 0.3f
        val textStyle = TextStyle(textGeometricTransform = textTransform)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.textGeometricTransform).isNull()
    }

    @Test
    fun `lerp textGeometricTransform with a is Null and t is larger than half`() {
        val textTransform = TextGeometricTransform(scaleX = 1.5f)
        val t = 0.7f
        val textStyle = TextStyle(textGeometricTransform = textTransform)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.textGeometricTransform).isEqualTo(textTransform)
    }

    @Test
    fun `lerp textGeometricTransform with b is Null and t is smaller than half`() {
        val textTransform = TextGeometricTransform(scaleX = 1.5f)
        val t = 0.3f
        val textStyle = TextStyle(textGeometricTransform = textTransform)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.textGeometricTransform).isEqualTo(textTransform)
    }

    @Test
    fun `lerp textGeometricTransform with b is Null and t is larger than half`() {
        val textTransform = TextGeometricTransform(scaleX = 1.5f)
        val t = 0.7f
        val textStyle = TextStyle(textGeometricTransform = textTransform)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.textGeometricTransform).isNull()
    }

    @Test
    fun `lerp textGeometricTransform with a and b are not Null`() {
        val textTransform1 = TextGeometricTransform(scaleX = 1.5f, skewX = 0.1f)
        val textTransform2 = TextGeometricTransform(scaleX = 1.0f, skewX = 0.3f)
        val t = 0.3f
        val textStyle1 = TextStyle(
            textGeometricTransform = textTransform1,
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f
        )
        val textStyle2 = TextStyle(
            textGeometricTransform = textTransform2,
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.textGeometricTransform)
            .isEqualTo(lerp(textTransform1, textTransform2, t))
    }

    @Test
    fun `lerp height with a is Null and t is smaller than half`() {
        val height = 88.0f
        val t = 0.2f
        val textStyle = TextStyle(height = height)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.height).isNull()
    }

    @Test
    fun `lerp height with a is Null and t is larger than half`() {
        val height = 88.0f
        val t = 0.8f
        val textStyle = TextStyle(height = height)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.height).isEqualTo(height)
    }

    @Test
    fun `lerp height with b is Null and t is smaller than half`() {
        val height = 88.0f
        val t = 0.2f
        val textStyle = TextStyle(height = height)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.height).isEqualTo(height)
    }

    @Test
    fun `lerp height with b is Null and t is larger than half`() {
        val height = 88.0f
        val t = 0.8f
        val textStyle = TextStyle(height = height)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.height).isNull()
    }

    @Test
    fun `lerp height with a and b are not Null`() {
        val height1 = 88.0f
        val height2 = 128.0f
        val t = 0.8f
        val textStyle1 = TextStyle(
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = height1
        )
        val textStyle2 = TextStyle(
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = height2
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        // a + (b - a) * t = 88.0 + (128.0 - 88.0) * 0.8 = 120.0
        assertThat(newTextStyle?.height).isEqualTo(120.0f)
    }

    @Test
    fun `lerp locale with a is Null and t is smaller than half`() {
        val locale = Locale("en", "US")
        val t = 0.2f
        val textStyle = TextStyle(locale = locale)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.locale).isNull()
    }

    @Test
    fun `lerp locale with a is Null and t is larger than half`() {
        val locale = Locale("en", "US")
        val t = 0.8f
        val textStyle = TextStyle(locale = locale)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.locale).isEqualTo(locale)
    }

    @Test
    fun `lerp locale with b is Null and t is smaller than half`() {
        val locale = Locale("en", "US")
        val t = 0.2f
        val textStyle = TextStyle(locale = locale)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.locale).isEqualTo(locale)
    }

    @Test
    fun `lerp locale with b is Null and t is larger than half`() {
        val locale = Locale("en", "US")
        val t = 0.8f
        val textStyle = TextStyle(locale = locale)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.locale).isNull()
    }

    @Test
    fun `lerp locale with a and b are not Null and t is smaller than half`() {
        val locale1 = Locale("en", "US")
        val locale2 = Locale("ja", "JP")
        val t = 0.3f
        val textStyle1 = TextStyle(
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f,
            locale = locale1
        )
        val textStyle2 = TextStyle(
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f,
            locale = locale2
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.locale).isEqualTo(locale1)
    }

    @Test
    fun `lerp locale with a and b are not Null and t is larger than half`() {
        val locale1 = Locale("en", "US")
        val locale2 = Locale("ja", "JP")
        val t = 0.8f
        val textStyle1 = TextStyle(
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f,
            locale = locale1
        )
        val textStyle2 = TextStyle(
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f,
            locale = locale2
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.locale).isEqualTo(locale2)
    }

    @Test
    fun `lerp background with a is Null and t is smaller than half`() {
        val color = Color(0xFF00FF00.toInt())
        val t = 0.2f
        val textStyle = TextStyle(background = color)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.background).isNull()
    }

    @Test
    fun `lerp background with a is Null and t is larger than half`() {
        val color = Color(0xFF00FF00.toInt())
        val t = 0.8f
        val textStyle = TextStyle(background = color)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.background).isEqualTo(color)
    }

    @Test
    fun `lerp background with b is Null and t is smaller than half`() {
        val color = Color(0xFF00FF00.toInt())
        val t = 0.2f
        val textStyle = TextStyle(background = color)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.background).isEqualTo(color)
    }

    @Test
    fun `lerp background with b is Null and t is larger than half`() {
        val t = 0.8f
        val textStyle = TextStyle(background = Color(0xFF00FF00.toInt()))

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.background).isNull()
    }

    @Test
    fun `lerp background with a and b are not Null and t is smaller than half`() {
        val color1 = Color(0x0)
        val color2 = Color(0xf)
        val t = 0.2f
        val textStyle1 = TextStyle(
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f,
            background = color1
        )
        val textStyle2 = TextStyle(
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f,
            background = color2
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.background).isEqualTo(color1)
    }

    @Test
    fun `lerp background with a and b are not Null and t is larger than half`() {
        val color1 = Color(0x0)
        val color2 = Color(0xf)
        val t = 0.8f
        val textStyle1 = TextStyle(
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f,
            background = color1
        )
        val textStyle2 = TextStyle(
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f,
            background = color2
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.background).isEqualTo(color2)
    }

    @Test
    fun `lerp decoration with a is Null and t is smaller than half`() {
        val decoration = TextDecoration.lineThrough
        val t = 0.2f
        val textStyle = TextStyle(decoration = decoration)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.decoration).isNull()
    }

    @Test
    fun `lerp decoration with a is Null and t is larger than half`() {
        val decoration = TextDecoration.lineThrough
        val t = 0.8f
        val textStyle = TextStyle(decoration = decoration)

        val newTextStyle = TextStyle.lerp(b = textStyle, t = t)

        assertThat(newTextStyle?.decoration).isEqualTo(decoration)
    }

    @Test
    fun `lerp decoration with b is Null and t is smaller than half`() {
        val decoration = TextDecoration.lineThrough
        val t = 0.2f
        val textStyle = TextStyle(decoration = decoration)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.decoration).isEqualTo(decoration)
    }

    @Test
    fun `lerp decoration with b is Null and t is larger than half`() {
        val decoration = TextDecoration.lineThrough
        val t = 0.8f
        val textStyle = TextStyle(decoration = decoration)

        val newTextStyle = TextStyle.lerp(a = textStyle, t = t)

        assertThat(newTextStyle?.decoration).isNull()
    }

    @Test
    fun `lerp decoration with a and b are not Null and t is smaller than half`() {
        val decoration1 = TextDecoration.lineThrough
        val decoration2 = TextDecoration.underline
        val t = 0.2f
        val textStyle1 = TextStyle(
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f,
            decoration = decoration1
        )
        val textStyle2 = TextStyle(
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f,
            decoration = decoration2
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.decoration).isEqualTo(decoration1)
    }

    @Test
    fun `lerp decoration with a and b are not Null and t is larger than half`() {
        val decoration1 = TextDecoration.lineThrough
        val decoration2 = TextDecoration.underline
        val t = 0.8f
        val textStyle1 = TextStyle(
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f,
            decoration = decoration1
        )
        val textStyle2 = TextStyle(
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f,
            decoration = decoration2
        )

        val newTextStyle = TextStyle.lerp(a = textStyle1, b = textStyle2, t = t)

        assertThat(newTextStyle?.decoration).isEqualTo(decoration2)
    }

    @Test
    fun `lerp returns debugLabel when both a and b's debugLabel are Null`() {
        val textStyle1 = TextStyle(
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f
        )
        val textStyle2 = TextStyle(
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f
        )

        val newTextStyle = TextStyle.lerp(textStyle1, textStyle2, 0.2f)

        assertThat(newTextStyle?.debugLabel).isEqualTo("lerp(unknown ⎯0.2→ unknown)")
    }

    @Test
    fun `lerp returns debugLabel when a's debugLabel is Null`() {
        val textStyle1 = TextStyle(
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f
        )
        val textStyle2 = TextStyle(
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f,
            debugLabel = "foo"
        )

        val newTextStyle = TextStyle.lerp(textStyle1, textStyle2, 0.2f)

        assertThat(newTextStyle?.debugLabel).isEqualTo("lerp(unknown ⎯0.2→ foo)")
    }

    @Test
    fun `lerp returns debugLabel when b's debugLabel is Null`() {
        val textStyle1 = TextStyle(
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f,
            debugLabel = "foo"
        )
        val textStyle2 = TextStyle(
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f
        )

        val newTextStyle = TextStyle.lerp(textStyle1, textStyle2, 0.2f)

        assertThat(newTextStyle?.debugLabel).isEqualTo("lerp(foo ⎯0.2→ unknown)")
    }

    @Test
    fun `lerp returns debugLabel when both debugLabels are not Null`() {
        val textStyle1 = TextStyle(
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f,
            debugLabel = "foo"
        )
        val textStyle2 = TextStyle(
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f,
            debugLabel = "bar"
        )

        val newTextStyle = TextStyle.lerp(textStyle1, textStyle2, 0.2f)

        assertThat(newTextStyle?.debugLabel).isEqualTo("lerp(foo ⎯0.2→ bar)")
    }

    @Test
    fun `lerp returns chained debugLabel`() {
        val other = TextStyle(
            fontSize = 4.0f,
            wordSpacing = 1.0f,
            letterSpacing = 2.0f,
            height = 123.0f,
            debugLabel = "foo"
        )
        val bar = TextStyle(
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f,
            debugLabel = "bar"
        )
        val baz = TextStyle(
            fontSize = 7.0f,
            wordSpacing = 2.0f,
            letterSpacing = 4.0f,
            height = 20.0f,
            debugLabel = "baz"
        )

        val newTextStyle = TextStyle.lerp(TextStyle.lerp(other, bar, 0.2f), baz, 0.8f)

        assertThat(newTextStyle?.debugLabel).isEqualTo("lerp(lerp(foo ⎯0.2→ bar) ⎯0.8→ baz)")
    }

    @Test
    fun `getTextStyle`() {
        val fontSize = 10.0f
        val height = 123.0f
        val color = Color(0xFF00FF00.toInt())
        val fontSynthesis = FontSynthesis.style
        val fontFeatureSettings = "\"kern\" 0"
        val baselineShift = BaselineShift.SUPERSCRIPT
        val textStyle = TextStyle(
            fontSize = fontSize,
            fontWeight = FontWeight.w800,
            color = color,
            height = height,
            fontSynthesis = fontSynthesis,
            fontFeatureSettings = fontFeatureSettings,
            baselineShift = baselineShift
        )

        assertThat(textStyle.fontFamily).isNull()
        assertThat(textStyle.fontSize).isEqualTo(fontSize)
        assertThat(textStyle.fontWeight).isEqualTo(FontWeight.w800)
        assertThat(textStyle.height).isEqualTo(height)
        assertThat(textStyle.color).isEqualTo(color)
        assertThat(textStyle.fontFeatureSettings).isEqualTo(fontFeatureSettings)

        val newTextStyle = textStyle.getTextStyle()

        assertThat(newTextStyle).isEqualTo(
            androidx.ui.engine.text.TextStyle(
                color = color,
                fontWeight = FontWeight.w800,
                fontSize = fontSize,
                height = height,
                fontSynthesis = fontSynthesis,
                fontFeatureSettings = fontFeatureSettings,
                baselineShift = baselineShift
            )
        )
    }

    @Test
    fun `getParagraphStyle with text align`() {
        val fontSize = 10.0f
        val height = 123.0f
        val color = Color(0xFF00FF00.toInt())
        val fontSynthesis = FontSynthesis.style
        val textStyle = TextStyle(
            fontSize = fontSize,
            fontWeight = FontWeight.w800,
            color = color,
            height = height,
            fontSynthesis = fontSynthesis
        )

        assertThat(textStyle.fontFamily).isNull()
        assertThat(textStyle.fontSize).isEqualTo(fontSize)
        assertThat(textStyle.fontWeight).isEqualTo(FontWeight.w800)
        assertThat(textStyle.height).isEqualTo(height)
        assertThat(textStyle.color).isEqualTo(color)

        val paragraphStyle = textStyle.getParagraphStyle(textAlign = TextAlign.CENTER)

        assertThat(paragraphStyle).isEqualTo(
            ParagraphStyle(
                textAlign = TextAlign.CENTER,
                fontWeight = FontWeight.w800,
                fontSize = fontSize,
                lineHeight = height,
                fontSynthesis = fontSynthesis
            )
        )
    }

    @Test
    fun `getParagraphStyle with LTR text direction`() {
        val defaultFontSize = 14.0f

        val paragraphStyleLTR = TextStyle().getParagraphStyle(textDirection = TextDirection.LTR)

        assertThat(paragraphStyleLTR).isEqualTo(
            ParagraphStyle(
                textDirection = TextDirection.LTR,
                fontSize = defaultFontSize
            )
        )
    }

    @Test
    fun `getParagraphStyle with RTL text direction`() {
        val defaultFontSize = 14.0f

        val paragraphStyleRTL = TextStyle().getParagraphStyle(textDirection = TextDirection.RTL)

        assertThat(paragraphStyleRTL).isEqualTo(
            ParagraphStyle(
                textDirection = TextDirection.RTL,
                fontSize = defaultFontSize
            )
        )
    }

    @Test
    fun `debugLabel with constructor default values`() {
        val unknown = TextStyle()

        assertThat(unknown.debugLabel).isNull()
    }

    @Test
    fun `debugLabel with constructor customized values`() {
        val textStyle = TextStyle(debugLabel = "foo", fontSize = 1.0f)

        assertThat(textStyle.debugLabel).isEqualTo("foo")
    }

    @Test
    fun `compareTo with same textStyle returns IDENTICAL`() {
        val textStyle = TextStyle()

        assertThat(textStyle.compareTo(textStyle)).isEqualTo(RenderComparison.IDENTICAL)
    }

    @Test
    fun `compareTo with identical textStyle returns IDENTICAL`() {
        val textStyle1 = TextStyle()
        val textStyle2 = TextStyle()

        assertThat(textStyle1.compareTo(textStyle2)).isEqualTo(RenderComparison.IDENTICAL)
    }

    @Test
    fun `compareTo textStyle with different layout returns LAYOUT`() {
        val fontSize = 10.0f
        val height = 123.0f
        val bgColor = Color(0xFFFFFF00.toInt())
        val fontFeatureSettings = "\"kern\" 0"

        val textStyle = TextStyle(
            inherit = false,
            color = null,
            fontSize = fontSize,
            fontWeight = FontWeight.w800,
            fontStyle = FontStyle.italic,
            fontFeatureSettings = fontFeatureSettings,
            letterSpacing = 1.0f,
            wordSpacing = 2.0f,
            textBaseline = TextBaseline.alphabetic,
            baselineShift = BaselineShift.SUBSCRIPT,
            textGeometricTransform = TextGeometricTransform(scaleX = 1.0f),
            height = height,
            locale = Locale("en", "US"),
            background = bgColor,
            decoration = TextDecoration.underline,
            debugLabel = "foo",
            fontFamily = FontFamily(genericFamily = "sans-serif")
        )

        assertThat(
            textStyle.compareTo(textStyle.copy(inherit = true))
        ).isEqualTo(RenderComparison.LAYOUT)

        assertThat(
            textStyle.compareTo(
                textStyle.copy(fontFamily = FontFamily(genericFamily = "monospace"))
            )
        ).isEqualTo(RenderComparison.LAYOUT)

        assertThat(textStyle.compareTo(textStyle.copy(fontSize = 20.0f)))
            .isEqualTo(RenderComparison.LAYOUT)

        assertThat(textStyle.compareTo(textStyle.copy(fontWeight = FontWeight.w100)))
            .isEqualTo(RenderComparison.LAYOUT)

        assertThat(textStyle.compareTo(textStyle.copy(fontStyle = FontStyle.normal)))
            .isEqualTo(RenderComparison.LAYOUT)

        assertThat(textStyle.compareTo(textStyle.copy(fontSynthesis = FontSynthesis.style)))
            .isEqualTo(RenderComparison.LAYOUT)

        assertThat(textStyle.compareTo(textStyle.copy(fontFeatureSettings = null)))
            .isEqualTo(RenderComparison.LAYOUT)

        assertThat(textStyle.compareTo(textStyle.copy(letterSpacing = 2.0f)))
            .isEqualTo(RenderComparison.LAYOUT)

        assertThat(textStyle.compareTo(textStyle.copy(wordSpacing = 4.0f)))
            .isEqualTo(RenderComparison.LAYOUT)

        assertThat(textStyle.compareTo(textStyle.copy(textBaseline = TextBaseline.ideographic)))
            .isEqualTo(RenderComparison.LAYOUT)

        assertThat(textStyle.compareTo(textStyle.copy(baselineShift = BaselineShift.SUPERSCRIPT)))
            .isEqualTo(RenderComparison.LAYOUT)

        assertThat(textStyle.compareTo(textStyle
            .copy(textGeometricTransform = TextGeometricTransform())))
            .isEqualTo(RenderComparison.LAYOUT)

        assertThat(textStyle.compareTo(textStyle.copy(height = 20.0f)))
            .isEqualTo(RenderComparison.LAYOUT)

        assertThat(textStyle.compareTo(textStyle.copy(locale = Locale("ja", "JP"))))
            .isEqualTo(RenderComparison.LAYOUT)
    }

    @Test
    fun `compareTo textStyle with different paint returns paint`() {
        val fontSize = 10.0f
        val height = 123.0f
        val color1 = Color(0xFF00FF00.toInt())
        val color2 = Color(0x00FFFF00)

        val textStyle = TextStyle(
            inherit = false,
            color = color1,
            fontSize = fontSize,
            fontWeight = FontWeight.w800,
            fontStyle = FontStyle.italic,
            letterSpacing = 1.0f,
            wordSpacing = 2.0f,
            textBaseline = TextBaseline.alphabetic,
            baselineShift = BaselineShift.SUPERSCRIPT,
            textGeometricTransform = TextGeometricTransform(null, null),
            height = height,
            locale = Locale("en", "US"),
            decoration = TextDecoration.underline,
            debugLabel = "foo",
            fontFamily = FontFamily(genericFamily = "sans-serif")
        )

        assertThat(textStyle.compareTo(textStyle.copy(color = color2)))
            .isEqualTo(RenderComparison.PAINT)

        assertThat(textStyle.compareTo(textStyle.copy(decoration = TextDecoration.lineThrough)))
            .isEqualTo(RenderComparison.PAINT)
    }
}
