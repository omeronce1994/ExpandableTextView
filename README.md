# ExpandableTextView
Expandable Text View Like Instagram


[![](https://jitpack.io/v/omeronce1994/ExpandableTextView.svg)](https://jitpack.io/#omeronce1994/ExpandableTextView)

# Install

Under your root build

    allprojects {
        repositories {
            ...
            ...
            maven { url 'https://jitpack.io' }
        }
        
and under your app build

    dependencies {
        ...
        implementation 'com.github.omeronce1994:ExpandableTextView:v1.0.0'
        
    }
    
# Usage

Set the max lines to select how many line will be shown until expanding "more" text.

Set the expanding text string:

    expandableTextView.setExpandingText("More...")
    
Set the expanding text color

    expandingTextView.setTextColor(resources.getColor(R.color.colorAccent))
    
Set the expanding text underline 
    
    expandingTextView.setUnderLineExpandingText(false)

XML:

    <expandabletv.omerco.com.expandabletv.ExpandableTextView
            android:id="@+id/text"
            android:layout_width="100dp"
            android:layout_height="wrap_content"
            android:text="Hello World! Check this awesome Instagram like TextView"
            android:maxLines="2"
            android:gravity="left"
            app:expandedText="@string/expanded_text"
            app:expandedTextColor="@color/colorAccent"
            app:setUnderLine="true"
        />

# License
    
    Copyright (C) 2017 Omer Cohen(omeronce1994)
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
                            
