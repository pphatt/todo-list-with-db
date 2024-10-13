package app.reminder.presentation.core.components.material

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

internal enum class ColorSchemeKeyTokens {
    Background,
    Error,
    ErrorContainer,
    InverseOnSurface,
    InversePrimary,
    InverseSurface,
    OnBackground,
    OnError,
    OnErrorContainer,
    OnPrimary,
    OnPrimaryContainer,
    OnPrimaryFixed,
    OnPrimaryFixedVariant,
    OnSecondary,
    OnSecondaryContainer,
    OnSecondaryFixed,
    OnSecondaryFixedVariant,
    OnSurface,
    OnSurfaceVariant,
    OnTertiary,
    OnTertiaryContainer,
    OnTertiaryFixed,
    OnTertiaryFixedVariant,
    Outline,
    OutlineVariant,
    Primary,
    PrimaryContainer,
    PrimaryFixed,
    PrimaryFixedDim,
    Scrim,
    Secondary,
    SecondaryContainer,
    SecondaryFixed,
    SecondaryFixedDim,
    Surface,
    SurfaceBright,
    SurfaceContainer,
    SurfaceContainerHigh,
    SurfaceContainerHighest,
    SurfaceContainerLow,
    SurfaceContainerLowest,
    SurfaceDim,
    SurfaceTint,
    SurfaceVariant,
    Tertiary,
    TertiaryContainer,
    TertiaryFixed,
    TertiaryFixedDim,
}

internal enum class ShapeKeyTokens {
    CornerExtraLarge,
    CornerExtraLargeTop,
    CornerExtraSmall,
    CornerExtraSmallTop,
    CornerFull,
    CornerLarge,
    CornerLargeEnd,
    CornerLargeTop,
    CornerMedium,
    CornerNone,
    CornerSmall,
}

internal enum class TypographyKeyTokens {
    BodyLarge,
    BodyMedium,
    BodySmall,
    DisplayLarge,
    DisplayMedium,
    DisplaySmall,
    HeadlineLarge,
    HeadlineMedium,
    HeadlineSmall,
    LabelLarge,
    LabelMedium,
    LabelSmall,
    TitleLarge,
    TitleMedium,
    TitleSmall,
}

internal object NavigationDrawerTokens {
    val ActiveFocusIconColor = ColorSchemeKeyTokens.OnSecondaryContainer
    val ActiveFocusLabelTextColor = ColorSchemeKeyTokens.OnSecondaryContainer
    val ActiveHoverIconColor = ColorSchemeKeyTokens.OnSecondaryContainer
    val ActiveHoverLabelTextColor = ColorSchemeKeyTokens.OnSecondaryContainer
    val ActiveIconColor = ColorSchemeKeyTokens.OnSecondaryContainer
    val ActiveIndicatorColor = ColorSchemeKeyTokens.SecondaryContainer
    val ActiveIndicatorHeight = 56.0.dp
    val ActiveIndicatorShape = ShapeKeyTokens.CornerFull
    val ActiveIndicatorWidth = 336.0.dp
    val ActiveLabelTextColor = ColorSchemeKeyTokens.OnSecondaryContainer
    val ActivePressedIconColor = ColorSchemeKeyTokens.OnSecondaryContainer
    val ActivePressedLabelTextColor = ColorSchemeKeyTokens.OnSecondaryContainer
    val BottomContainerShape = ShapeKeyTokens.CornerLargeTop
    val ContainerHeightPercent = 100.0f
    val ContainerShape = ShapeKeyTokens.CornerLargeEnd
    val ContainerWidth = 360.0.dp
    val FocusIndicatorColor = ColorSchemeKeyTokens.Secondary
    val HeadlineColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val HeadlineFont = TypographyKeyTokens.TitleSmall
    val IconSize = 24.0.dp
    val InactiveFocusIconColor = ColorSchemeKeyTokens.OnSurface
    val InactiveFocusLabelTextColor = ColorSchemeKeyTokens.OnSurface
    val InactiveHoverIconColor = ColorSchemeKeyTokens.OnSurface
    val InactiveHoverLabelTextColor = ColorSchemeKeyTokens.OnSurface
    val InactiveIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val InactiveLabelTextColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val InactivePressedIconColor = ColorSchemeKeyTokens.OnSurface
    val InactivePressedLabelTextColor = ColorSchemeKeyTokens.OnSurface
    val LabelTextFont = TypographyKeyTokens.LabelLarge
    val LargeBadgeLabelColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val LargeBadgeLabelFont = TypographyKeyTokens.LabelLarge
    val ModalContainerColor = ColorSchemeKeyTokens.SurfaceContainerLow
    val ModalContainerElevation = ElevationTokens.Level1
    val StandardContainerColor = ColorSchemeKeyTokens.Surface
    val StandardContainerElevation = ElevationTokens.Level0
}

internal object ElevationTokens {
    val Level0 = 0.0.dp
    val Level1 = 1.0.dp
    val Level2 = 3.0.dp
    val Level3 = 6.0.dp
    val Level4 = 8.0.dp
    val Level5 = 12.0.dp
}

internal fun Shapes.fromToken(value: ShapeKeyTokens): Shape {
    return when (value) {
        ShapeKeyTokens.CornerExtraLarge -> extraLarge
        ShapeKeyTokens.CornerExtraLargeTop -> extraLarge.top()
        ShapeKeyTokens.CornerExtraSmall -> extraSmall
        ShapeKeyTokens.CornerExtraSmallTop -> extraSmall.top()
        ShapeKeyTokens.CornerFull -> CircleShape
        ShapeKeyTokens.CornerLarge -> large
        ShapeKeyTokens.CornerLargeEnd -> large.end()
        ShapeKeyTokens.CornerLargeTop -> large.top()
        ShapeKeyTokens.CornerMedium -> medium
        ShapeKeyTokens.CornerNone -> RectangleShape
        ShapeKeyTokens.CornerSmall -> small
    }
}

internal fun CornerBasedShape.end(): CornerBasedShape {
    return copy(topStart = CornerSize(0.0.dp), bottomStart = CornerSize(0.0.dp))
}

internal fun CornerBasedShape.top(): CornerBasedShape {
    return copy(bottomStart = CornerSize(0.0.dp), bottomEnd = CornerSize(0.0.dp))
}

internal val ShapeKeyTokens.value: Shape
    @Composable @ReadOnlyComposable get() = MaterialTheme.shapes.fromToken(this)

/**
 * Material Design navigation drawer item.
 *
 * A [NavigationDrawerItem] represents a destination within drawers, either [ModalNavigationDrawer],
 * [PermanentNavigationDrawer] or [DismissibleNavigationDrawer].
 *
 * @sample androidx.compose.material3.samples.ModalNavigationDrawerSample
 *
 * @param label text label for this item
 * @param selected whether this item is selected
 * @param onClick called when this item is clicked
 * @param modifier the [Modifier] to be applied to this item
 * @param icon optional icon for this item, typically an [Icon]
 * @param badge optional badge to show on this item from the end side
 * @param colors [NavigationDrawerItemColors] that will be used to resolve the colors used for this
 *   item in different states. See [NavigationDrawerItemDefaults.colors].
 * @param interactionSource an optional hoisted [MutableInteractionSource] for observing and
 *   emitting [Interaction]s for this item. You can use this to change the item's appearance or
 *   preview the item in different states. Note that if `null` is provided, interactions will still
 *   happen internally.
 */
@Composable
fun NavigationDrawerItem(
    label: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null,
    shape: Shape = NavigationDrawerTokens.ActiveIndicatorShape.value,
    colors: NavigationDrawerItemColors = NavigationDrawerItemDefaults.colors(),
    interactionSource: MutableInteractionSource? = null
) {
    Surface(
        selected = selected,
        onClick = onClick,
        modifier =
        modifier
            .semantics { role = Role.Tab }
            .heightIn(min = 40.dp)
            .fillMaxWidth(),
        shape = shape,
        color = colors.containerColor(selected).value,
        interactionSource = interactionSource,
    ) {
        Row(
            Modifier.padding(start = 10.dp, end = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                val iconColor = colors.iconColor(selected).value
                CompositionLocalProvider(
                    value = LocalContentColor provides iconColor,
                    content = icon
                )
                Spacer(Modifier.width(12.dp))
            }
            Box(Modifier.weight(1f)) {
                val labelColor = colors.textColor(selected).value
                CompositionLocalProvider(
                    value = LocalContentColor provides labelColor,
                    content = label
                )
            }
            if (badge != null) {
                Spacer(Modifier.width(12.dp))
                val badgeColor = colors.badgeColor(selected).value
                CompositionLocalProvider(
                    value = LocalContentColor provides badgeColor,
                    content = badge
                )
            }
        }
    }
}
