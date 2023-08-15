package algo.booking;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <div class="flex h-full w-full flex-1 flex-col"><div class="w-full px-5 pt-5"><div class="w-full"><div class="flex space-x-4"><div class="flex-1"><div class="flex h-full items-center"><a class="mr-2 text-label-1 dark:text-dark-label-1 hover:text-label-1 dark:hover:text-dark-label-1 text-lg font-medium" href="/problems/evaluate-division/">399. Evaluate Division</a><div class="inline-flex min-h-[20px] items-center space-x-2 align-top mt-1"><div class="inline-flex items-center space-x-2"></div></div></div></div><div class="flex items-center"><div class="inline-flex gap-2 text-lg"><div class="popover-wrapper inline-block" data-headlessui-state=""><div><div id="headlessui-popover-button-:r1u:" aria-expanded="false" data-headlessui-state=""><div class="px-2 py-1 hover:text-blue-s dark:hover:text-dark-blue-s cursor-pointer rounded transition-colors text-gray-6 dark:text-dark-gray-6 hover:bg-fill-3 dark:hover:bg-dark-fill-3"><svg xmlns="http://www.w3.org/2000/svg" width="23" height="16" viewBox="0 0 23 16" fill="currentColor"><path d="M1.48535 3.08496C1.69271 3.08496 1.87256 3.15902 2.0249 3.30713C2.17725 3.45947 2.25342 3.64144 2.25342 3.85303V7.26807H5.77002V3.85303C5.77002 3.64144 5.84619 3.45947 5.99854 3.30713C6.15088 3.15902 6.33073 3.08496 6.53809 3.08496H6.55713C6.76449 3.08496 6.94434 3.15902 7.09668 3.30713C7.24902 3.45947 7.3252 3.64144 7.3252 3.85303V12.2319C7.3252 12.4478 7.24902 12.6276 7.09668 12.7715C6.94434 12.9238 6.76449 13 6.55713 13H6.53809C6.33073 13 6.15088 12.9238 5.99854 12.7715C5.84619 12.6276 5.77002 12.4478 5.77002 12.2319V8.81689H2.25342V12.2319C2.25342 12.4478 2.17725 12.6276 2.0249 12.7715C1.87256 12.9238 1.69271 13 1.48535 13H1.46631C1.25895 13 1.0791 12.9238 0.926758 12.7715C0.774414 12.6276 0.698242 12.4478 0.698242 12.2319V3.85303C0.698242 3.64144 0.774414 3.45947 0.926758 3.30713C1.0791 3.15902 1.25895 3.08496 1.46631 3.08496H1.48535ZM9.68018 5.6875C9.896 5.6875 10.0758 5.76367 10.2197 5.91602C10.3721 6.06836 10.4482 6.25033 10.4482 6.46191V12.2319C10.4482 12.4478 10.3721 12.6276 10.2197 12.7715C10.0758 12.9238 9.896 13 9.68018 13H9.66748C9.45589 13 9.27393 12.9238 9.12158 12.7715C8.96924 12.6276 8.89307 12.4478 8.89307 12.2319V6.46191C8.89307 6.25033 8.96924 6.06836 9.12158 5.91602C9.27393 5.76367 9.45589 5.6875 9.66748 5.6875H9.68018ZM9.67383 4.8623C9.42839 4.8623 9.21891 4.77555 9.04541 4.60205C8.87191 4.42855 8.78516 4.21908 8.78516 3.97363C8.78516 3.73242 8.87191 3.52507 9.04541 3.35156C9.21891 3.17806 9.42839 3.09131 9.67383 3.09131C9.91504 3.09131 10.1224 3.17806 10.2959 3.35156C10.4736 3.52083 10.5625 3.72819 10.5625 3.97363C10.5625 4.22331 10.4736 4.43278 10.2959 4.60205C10.1224 4.77555 9.91504 4.8623 9.67383 4.8623ZM13.5142 12.2319C13.5142 12.4478 13.438 12.6276 13.2856 12.7715C13.1333 12.9238 12.9535 13 12.7461 13H12.7271C12.5197 13 12.3398 12.9238 12.1875 12.7715C12.0352 12.6276 11.959 12.4478 11.959 12.2319V8.75977C11.959 7.91341 12.2594 7.18978 12.8604 6.58887C13.4613 5.98796 14.1849 5.6875 15.0312 5.6875C15.8734 5.6875 16.5949 5.98796 17.1958 6.58887C17.7967 7.18978 18.0972 7.91341 18.0972 8.75977V12.2319C18.0972 12.4478 18.021 12.6276 17.8687 12.7715C17.7248 12.9238 17.5449 13 17.3291 13H17.3164C17.1048 13 16.9229 12.9238 16.7705 12.7715C16.6182 12.6276 16.542 12.4478 16.542 12.2319V8.75977C16.542 8.34082 16.396 7.98324 16.104 7.68701C15.8078 7.39079 15.4502 7.24268 15.0312 7.24268C14.6081 7.24268 14.2484 7.39079 13.9521 7.68701C13.6602 7.98324 13.5142 8.34082 13.5142 8.75977V12.2319ZM20.1602 3.08496C20.3675 3.08496 20.5474 3.15902 20.6997 3.30713C20.8521 3.45947 20.9282 3.64144 20.9282 3.85303V5.6875H21.8804C22.0877 5.6875 22.2676 5.76367 22.4199 5.91602C22.5723 6.06836 22.6484 6.25033 22.6484 6.46191V6.47461C22.6484 6.69043 22.5723 6.87028 22.4199 7.01416C22.2676 7.1665 22.0877 7.24268 21.8804 7.24268H20.9282V11.0068C20.9282 11.1296 20.9705 11.2332 21.0552 11.3179C21.1398 11.4025 21.2456 11.4448 21.3726 11.4448H21.8804C22.0877 11.4448 22.2676 11.521 22.4199 11.6733C22.5723 11.8257 22.6484 12.0076 22.6484 12.2192V12.2319C22.6484 12.4478 22.5723 12.6276 22.4199 12.7715C22.2676 12.9238 22.0877 13 21.8804 13H21.3726C20.8224 13 20.3506 12.8053 19.957 12.416C19.5677 12.0225 19.373 11.5527 19.373 11.0068V3.85303C19.373 3.64144 19.4492 3.45947 19.6016 3.30713C19.7539 3.15902 19.9338 3.08496 20.1411 3.08496H20.1602Z"></path></svg></div></div></div></div><div class="popover-wrapper inline-block" data-headlessui-state=""><div><div id="headlessui-popover-button-:rj:" aria-expanded="false" data-headlessui-state=""><div class="cursor-pointer rounded p-[3px] text-gray-6 transition-colors dark:text-dark-gray-6 hover:bg-fill-3 dark:hover:bg-dark-fill-3"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em" height="1em" fill="currentColor"><path fill-rule="evenodd" d="M12 2c5.523 0 10 4.477 10 10s-4.477 10-10 10S2 17.523 2 12 6.477 2 12 2zm0 2a8 8 0 100 16 8 8 0 000-16zm-4.998 9.27a1.25 1.25 0 100-2.5 1.25 1.25 0 000 2.5zm6.25-1.25a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zm3.75 1.25a1.25 1.25 0 100-2.5 1.25 1.25 0 000 2.5z" clip-rule="evenodd"></path></svg></div></div></div></div></div></div></div><div class="mt-3 flex items-center space-x-4"><div class="text-yellow dark:text-dark-yellow inline-block text-sm font-medium capitalize leading-[22px]">Medium</div><div class="flex items-center space-x-4"><div class="text-xstransition-colors flex cursor-pointer items-center space-x-1 rounded py-[3px] px-1 hover:bg-fill-3 dark:hover:bg-dark-fill-3 text-gray-6 dark:text-dark-gray-6"><div class="text-lg text-gray-6 dark:text-dark-gray-6"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em" height="1em" fill="currentColor"><path fill-rule="evenodd" d="M7.04 9.11l3.297-7.419a1 1 0 01.914-.594 3.67 3.67 0 013.67 3.671V7.33h4.028a2.78 2.78 0 012.78 3.2l-1.228 8.01a2.778 2.778 0 01-2.769 2.363H5.019a2.78 2.78 0 01-2.78-2.78V11.89a2.78 2.78 0 012.78-2.78H7.04zm-2.02 2a.78.78 0 00-.781.78v6.232c0 .431.35.78.78.78H6.69V11.11H5.02zm12.723 7.793a.781.781 0 00.781-.666l1.228-8.01a.78.78 0 00-.791-.898h-5.04a1 1 0 01-1-1V4.77c0-.712-.444-1.32-1.07-1.56L8.69 10.322v8.58h9.053z" clip-rule="evenodd"></path></svg></div><div class="text-xs">8.5K</div></div><div class="text-xstransition-colors flex cursor-pointer items-center space-x-1 rounded py-[3px] px-1 hover:bg-fill-3 dark:hover:bg-dark-fill-3 text-gray-6 dark:text-dark-gray-6"><div class="text-lg text-gray-6 dark:text-dark-gray-6"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em" height="1em" fill="currentColor"><path fill-rule="evenodd" d="M13.663 22.309a1 1 0 01-.914.594 3.67 3.67 0 01-3.67-3.671V16.67H5.05a2.78 2.78 0 01-2.78-3.2l1.228-8.01a2.778 2.778 0 012.769-2.364H18.98a2.78 2.78 0 012.78 2.781v6.232a2.78 2.78 0 01-2.78 2.78H16.96l-3.297 7.419zm5.318-9.419a.78.78 0 00.78-.78V5.878a.78.78 0 00-.78-.78H17.31v7.792h1.67zM6.257 5.097a.781.781 0 00-.781.666l-1.229 8.01a.78.78 0 00.792.898h5.04a1 1 0 011 1v3.56c0 .712.443 1.32 1.07 1.56l3.16-7.113v-8.58H6.258z" clip-rule="evenodd"></path></svg></div><div class="text-xs">769</div></div></div><div class="popover-wrapper inline-block" data-headlessui-state=""><div><div id="headlessui-popover-button-:ro:" aria-expanded="false" data-headlessui-state=""><div><div class="flex h-full cursor-pointer items-center rounded p-[3px] text-lg transition-colors hover:bg-fill-3 dark:hover:bg-dark-fill-3 text-gray-6 dark:text-dark-gray-6"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em" height="1em" fill="currentColor"><path fill-rule="evenodd" d="M11.394 2.074a2.5 2.5 0 011.212 0c.723.181 1.185.735 1.526 1.262.342.528.703 1.259 1.131 2.127l.392.795c.302.61.348.667.386.7a.502.502 0 00.086.063c.043.025.11.052.786.15l.877.128c.958.139 1.764.256 2.372.418.606.162 1.276.43 1.671 1.062a2.5 2.5 0 01.375 1.152c.052.744-.333 1.354-.728 1.841-.397.489-.98 1.058-1.674 1.733l-.634.619c-.489.476-.527.537-.548.583a.506.506 0 00-.033.101c-.01.05-.015.122.1.794l.15.873c.164.954.302 1.758.335 2.386.034.627-.014 1.346-.493 1.918a2.5 2.5 0 01-.98.712c-.692.279-1.39.102-1.976-.124-.588-.226-1.309-.605-2.165-1.056l-.785-.412c-.603-.317-.674-.335-.724-.34a.496.496 0 00-.106 0c-.05.005-.12.023-.724.34l-.785.412c-.856.45-1.577.83-2.165 1.056-.585.226-1.284.403-1.976.124a2.501 2.501 0 01-.98-.712c-.48-.572-.527-1.291-.493-1.918.033-.628.171-1.431.335-2.386l.15-.873c.115-.672.11-.745.1-.794a.5.5 0 00-.033-.101c-.02-.046-.06-.107-.548-.583l-.634-.619c-.694-.675-1.277-1.244-1.674-1.733-.395-.487-.78-1.097-.728-1.841a2.5 2.5 0 01.375-1.152c.395-.633 1.065-.9 1.67-1.062.61-.162 1.415-.28 2.373-.418l.877-.128c.675-.098.743-.125.786-.15a.5.5 0 00.086-.062c.038-.034.084-.09.386-.701l.392-.795c.428-.868.789-1.599 1.131-2.127.341-.527.803-1.08 1.526-1.262zm.493 1.939c-.023.013-.132.089-.34.41-.271.418-.58 1.042-1.045 1.982l-.364.738-.05.103c-.213.434-.428.872-.788 1.197a2.5 2.5 0 01-.43.312c-.42.241-.903.31-1.381.379a52.6 52.6 0 00-.114.016l-.815.119c-1.037.15-1.725.252-2.207.38-.37.099-.476.18-.495.197a.5.5 0 00-.07.216c.005.025.044.153.285.45.314.386.811.874 1.562 1.605l.59.575.082.08c.346.336.697.676.895 1.118.072.162.127.332.164.506.1.474.016.955-.067 1.431l-.02.113-.138.811c-.178 1.033-.294 1.72-.32 2.217-.02.382.023.508.034.532.05.058.113.103.183.133.026.003.16.006.516-.132.465-.18 1.082-.502 2.01-.99l.728-.382.102-.054c.427-.226.859-.454 1.34-.505.177-.02.355-.02.532 0 .481.051.913.28 1.34.505l.102.054.728.383c.928.487 1.545.81 2.01.99.357.137.49.134.516.13a.499.499 0 00.183-.132c.01-.024.055-.15.034-.532-.026-.497-.142-1.184-.32-2.217l-.139-.81-.02-.114c-.082-.476-.166-.957-.066-1.431.037-.174.092-.344.164-.506.198-.442.549-.782.895-1.118a20.8 20.8 0 00.083-.08l.59-.575c.75-.731 1.247-1.219 1.561-1.606.241-.296.28-.424.285-.45a.5.5 0 00-.07-.215c-.02-.017-.126-.098-.495-.196-.482-.129-1.17-.23-2.207-.381l-.815-.119-.113-.016c-.479-.068-.963-.138-1.382-.379a2.5 2.5 0 01-.43-.312c-.36-.325-.575-.763-.788-1.197a31.757 31.757 0 00-.05-.103l-.364-.738c-.464-.94-.774-1.564-1.045-1.982-.208-.321-.317-.397-.34-.41a.5.5 0 00-.226 0zm8.326 6.044v.002-.002zm-3.246 9.575h-.002.002zm-9.934 0h.002-.002zm-3.246-9.575v.002-.002z" clip-rule="evenodd"></path></svg></div></div></div></div></div><div class="popover-wrapper inline-block" data-headlessui-state=""><div><div id="headlessui-popover-button-:rr:" aria-expanded="false" data-headlessui-state=""><div class="flex h-full cursor-pointer items-center rounded p-[3px] text-lg text-dark-gray-6 transition-colors dark:text-dark-gray-6 hover:bg-fill-3 dark:hover:bg-dark-fill-3"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em" height="1em" fill="currentColor"><path fill-rule="evenodd" d="M11.5 5.5a7 7 0 107 7 1 1 0 112 0 9 9 0 11-9-9 1 1 0 110 2z" clip-rule="evenodd"></path><path fill-rule="evenodd" d="M20.207 3.793a1 1 0 010 1.414l-7 7a1 1 0 01-1.414-1.414l7-7a1 1 0 011.414 0z" clip-rule="evenodd"></path><path fill-rule="evenodd" d="M14.5 4.5a1 1 0 011-1h4a1 1 0 011 1v4a1 1 0 11-2 0v-3h-3a1 1 0 01-1-1z" clip-rule="evenodd"></path></svg></div></div></div></div></div></div></div><div class="px-5 pt-3"><div class="relative"><div class="inline-block"><div class="transition-colors flex cursor-pointer items-center space-x-1.5 rounded-[21px] px-2 py-1 text-xs hover:bg-opacity-20 bg-fill-3 dark:bg-dark-fill-3 text-label-2 dark:text-dark-label-2 hover:bg-fill-2 dark:hover:bg-dark-fill-2"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em" height="1em" fill="currentColor" class="text-brand-orange dark:text-dark-brand-orange"><path fill-rule="evenodd" d="M7 8v2H6a3 3 0 00-3 3v6a3 3 0 003 3h12a3 3 0 003-3v-6a3 3 0 00-3-3h-1V8A5 5 0 007 8zm8 0v2H9V8a3 3 0 116 0zm-3 6a2 2 0 100 4 2 2 0 000-4z" clip-rule="evenodd"></path></svg><span>Companies</span></div></div></div></div><div class="px-5 pt-4"><div class="flex"></div><div class="xFUwe" data-track-load="description_content"><p>You are given an array of variable pairs <code>equations</code> and an array of real numbers <code>values</code>, where <code>equations[i] = [A<sub>i</sub>, B<sub>i</sub>]</code> and <code>values[i]</code> represent the equation <code>A<sub>i</sub> / B<sub>i</sub> = values[i]</code>. Each <code>A<sub>i</sub></code> or <code>B<sub>i</sub></code> is a string that represents a single variable.</p>
 *
 * <p>You are also given some <code>queries</code>, where <code>queries[j] = [C<sub>j</sub>, D<sub>j</sub>]</code> represents the <code>j<sup>th</sup></code> query where you must find the answer for <code>C<sub>j</sub> / D<sub>j</sub> = ?</code>.</p>
 *
 * <p>Return <em>the answers to all queries</em>. If a single answer cannot be determined, return <code>-1.0</code>.</p>
 *
 * <p><strong>Note:</strong> The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.</p>
 *
 * <p><strong>Note:&nbsp;</strong>The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.</p>
 *
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 *
 * <pre><strong>Input:</strong> equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * <strong>Output:</strong> [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * <strong>Explanation:</strong>
 * Given: <em>a / b = 2.0</em>, <em>b / c = 3.0</em>
 * queries are: <em>a / c = ?</em>, <em>b / a = ?</em>, <em>a / e = ?</em>, <em>a / a = ?</em>, <em>x / x = ? </em>
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * note: x is undefined =&gt; -1.0</pre>
 *
 * <p><strong class="example">Example 2:</strong></p>
 *
 * <pre><strong>Input:</strong> equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * <strong>Output:</strong> [3.75000,0.40000,5.00000,0.20000]
 * </pre>
 *
 * <p><strong class="example">Example 3:</strong></p>
 *
 * <pre><strong>Input:</strong> equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * <strong>Output:</strong> [0.50000,2.00000,-1.00000,-1.00000]
 * </pre>
 *
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 *
 * <ul>
 * 	<li><code>1 &lt;= equations.length &lt;= 20</code></li>
 * 	<li><code>equations[i].length == 2</code></li>
 * 	<li><code>1 &lt;= A<sub>i</sub>.length, B<sub>i</sub>.length &lt;= 5</code></li>
 * 	<li><code>values.length == equations.length</code></li>
 * 	<li><code>0.0 &lt; values[i] &lt;= 20.0</code></li>
 * 	<li><code>1 &lt;= queries.length &lt;= 20</code></li>
 * 	<li><code>queries[i].length == 2</code></li>
 * 	<li><code>1 &lt;= C<sub>j</sub>.length, D<sub>j</sub>.length &lt;= 5</code></li>
 * 	<li><code>A<sub>i</sub>, B<sub>i</sub>, C<sub>j</sub>, D<sub>j</sub></code> consist of lower case English letters and digits.</li>
 * </ul>
 */
public class HotelPriceCurrencyDivision {
    final static double hotelPrice = 90.0; // in original hotel currency
    final static double fee = 0.5; // in user local currency

    public record Amount(double product, double depth){}

    public static Amount[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        /**
         * <pre>
         *     {
         *   "a": {
         *     "b": 2.0
         *   },
         *   "b": {
         *     "a": 0.5,
         *     "c": 3.0
         *   },
         *   "c": {
         *     "b": 0.33
         *   }
         * }
         * </pre>
         */
        // Initialize a currency graph to save each item division and its value.
        // We are also doing so for the inverse as well
        // i.e        : a/b = 2.0 => {a->{b->2.0}}
        // for inverse: b/a = 0.5 => {b, {a->0.5}}
        //              b/c = 3.0 => {b, {c->3.0}}
        //              c/b = 0.33 => {c, {b->0.33}}
        Map<String, Map<String, Double>> currencyGraph = new HashMap<>();

        int i = 0; // Keep track of index to get corresponding value

        // build the currency graph
        for(List<String> ls : equations){
            // put the current division in graph, first check if already present in graph in other to initialize inner map if not present
            if(!currencyGraph.containsKey(ls.get(0))) {
                currencyGraph.put(ls.get(0),  new HashMap<>());
            }
            currencyGraph.get(ls.get(0)).put(ls.get(1), values[i]);

            // Put the inverse of the current to graph, do check in case inverse key not already present
            if(!currencyGraph.containsKey(ls.get(1))) {
                currencyGraph.put(ls.get(1),  new HashMap<>());
            }
            currencyGraph.get(ls.get(1)).put(ls.get(0), (double) 1 / values[i]);

            i++;
        }


        Amount[] output = new Amount[queries.size()];

        // To find values of each query, we look for a path from the numerator of the query to the denominator.
        // eg
        for(i = 0; i<queries.size(); i++){
            if(currencyGraph.containsKey(queries.get(i).get(0)) && currencyGraph.containsKey(queries.get(i).get(1))){
                Set<String> vis = new HashSet<>();
                output[i] = dfsSearch(queries.get(i).get(0), queries.get(i).get(1), 1.0, 1, currencyGraph, vis);
            } else {
                output[i] = new Amount(-1, 0);
            }
        }
        return output;
    }


    private static Amount dfsSearch(String num, String deNum, double product, int depth, Map<String, Map<String, Double>> currencyGraph, Set<String> visited){
        if(num.equalsIgnoreCase(deNum)){
            return new Amount(product, depth);
        }

        if(visited.contains(num)){
            return new Amount(-1, depth);
        }

        visited.add(num);

        for (Entry<String, Double> mapping: currencyGraph.get(num).entrySet()){
            Amount outcome = dfsSearch(mapping.getKey(), deNum, product * mapping.getValue(), ++depth, currencyGraph, visited);

            if(outcome.product != -1){
                return outcome;
            }
        }

        return new Amount(-1, depth);
    }

    public static void main(String[] args) {
        List<List<String>> variables = List.of(
                List.of("EUR", "GBP"),
                List.of("GBP","CAD"),
                List.of("bc","cd")
        );

        double[] values = new double[]{1.5,2.5,5.0};

        List<List<String>> queries = List.of(
                List.of("EUR","CAD"),
                List.of("CAD","GBP"),
                List.of("bc","cd"),
                List.of("cd","bc"),
                List.of("CAD","EUR")
        );

        Amount[] amounts = calcEquation(variables, values, queries);

        System.out.println("Result of the queries are: ");
        System.out.println(Arrays.stream(amounts)
                .map(Amount::product)
                .map(Objects::toString)
                .collect(Collectors.joining(", ", "[", "]")));

        System.out.println("Result of the queries multiplied by hotel fee are: ");
        System.out.println(Arrays.stream(amounts)
                .peek(amount -> new Amount(amount.product * hotelPrice, amount.depth))
                .map(Amount::product)
                .map(Objects::toString)
                .collect(Collectors.joining(", ", "[", "]")));

        System.out.println("Result of the queries multiplied by hotel fee and adding transaction fees are: ");
        System.out.println(Arrays.stream(amounts)
                .peek(amount -> new Amount(amount.product * hotelPrice, amount.depth))
                .peek(amount -> new Amount(amount.product, amount.depth * fee))
                .map(amount -> amount.product + amount.depth)
                .map(Objects::toString)
                .collect(Collectors.joining(", ", "[", "]")));
    }
}
