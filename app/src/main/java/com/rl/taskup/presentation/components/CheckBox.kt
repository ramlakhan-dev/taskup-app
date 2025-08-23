package com.rl.taskup.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rl.taskup.R

@Composable
fun CheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    val bgColor = if (checked) MaterialTheme.colorScheme.primary else Color.Transparent
    val borderColor = if (checked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface

    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(bgColor)
            .border(1.dp,  borderColor, CircleShape)
            .clickable {
                onCheckedChange (!checked)
            },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(id = R.string.checked),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}